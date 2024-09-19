package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

/**
 * Use Builder Design Pattern
 */
public class Camera implements Cloneable {
    // Camera location
    private Point p0;

    private Vector vTo;
    private Vector vUp;
    private Vector vRight;

    private double viewPlaneHeight = 0.0;
    private double viewPlaneWidth = 0.0;
    private double viewPlaneDistance = 0.0;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    private int minRaysPerPixel = 2;
    private int maxRaysPerPixel = 4;
    private int differentColors = 3;

    /**
     * @return the Camera location
     */
    public Point getP0() {
        return p0;
    }

    /**
     * @return vTo, the vector go towards the view plane
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * @return vUp, the vector go towards the top
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * @return vRight, the vector go to the right
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * @return the height of the view plane
     */
    public double getViewPlaneHeight() {
        return viewPlaneHeight;
    }

    /**
     * @return the width of the view plane
     */
    public double getViewPlaneWidth() {
        return viewPlaneWidth;
    }

    /**
     * @return the distance from the camera to the view plane
     */
    public double getViewPlaneDistance() {
        return viewPlaneDistance;
    }

    private Camera() {
    }

    /**
     * @return the object of the build
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * construct the ray from the camera to the view plane
     *
     * @param nX           - number of the rows in the view plane
     * @param nY           - number of the columns in the view plane
     * @param j            - index column of the specific pixel
     * @param i            - index row of the specific pixel
     * @param raysPerPixel - number of rays to every pixel
     * @return the ray from the camera to the pixel in the view plane
     */
    public List<Ray> constructRay(int nX, int nY, int j, int i, int raysPerPixel) {
        List<Ray> rays = new LinkedList<>();
        Point pIJ = p0.add(vTo.scale(viewPlaneDistance));

        double rY = viewPlaneHeight / nY;
        double rX = viewPlaneWidth / nX;
        double offsetY = rY / raysPerPixel;
        double offsetX = rX / raysPerPixel;

        double xJ = (j - (nX - 1) / 2.0) * rX;
        double yI = -(i - (nY - 1) / 2.0) * rY;

//        // to prevent the formation of the zero vector
//        double epsilon = 0.0001;

        for (int x = 0; x < raysPerPixel; x++) {
            for (int y = 0; y < raysPerPixel; y++) {
                double xOffset = xJ + offsetX * (x - raysPerPixel / 2.0);
                double yOffset = yI + offsetY * (y - raysPerPixel / 2.0);

                Point pIJOffset = pIJ;
                if (!Util.isZero(xOffset)) {
                    pIJOffset = pIJOffset.add(vRight.scale(xOffset));
                }
                if (!Util.isZero(yOffset)) {
                    pIJOffset = pIJOffset.add(vUp.scale(yOffset));
                }

                Vector direction = pIJOffset.subtract(p0);

//                // Verify the direction vector is not zero
//                if (direction.length() == 0) {
//                    // Add a small offset along vTo direction to avoid zero vector
//                    direction = vTo.scale(epsilon);
//                }

                rays.add(new Ray(p0, direction.normalize()));
            }
        }

        return rays;
    }

    @Override
    public Camera clone() {
        try {
            Camera clone = (Camera) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Processes the image
     */
    public Camera renderImage() {
        for (int x = 0; x < imageWriter.getNx(); x++) {
            for (int y = 0; y < imageWriter.getNy(); y++) {
                castRay(imageWriter.getNx(), imageWriter.getNy(), x, y);
            }
        }
        return this;
    }

    /**
     * Casts a ray on a certain pixel and colors it
     *
     * @param nX - the number of rows in the view plane
     * @param nY - the number of columns in the view plane
     * @param x  - the row number of the pixel
     * @param y  - the column number of the pixel
     */
    private void castRay(int nX, int nY, int x, int y) {
        List<Ray> rays = constructRay(nX, nY, x, y, minRaysPerPixel);
        List<Color> colors = new LinkedList<>();
        for (Ray ray : rays) {
            colors.add(rayTracer.traceRay(ray));
        }

        Color color = Color.BLACK;
        // Adaptive Super-Sampling
        if (isAdaptiveSamplingNeeded(colors)) {
            List<Ray> additionalRays = constructRay(nX, nY, x, y, maxRaysPerPixel);
            for (Ray ray : additionalRays) {
                color = color.add(rayTracer.traceRay(ray));
            }
            color = color.reduce(additionalRays.size());
        } else {
            for (Color c : colors) {
                color = color.add(c);
            }
            color = color.reduce(rays.size());
        }

        imageWriter.writePixel(x, y, color);
    }

    // Adaptive Super-Sampling
    /**
     * help function - check the level of differant
     * @param colors - list of colors the check between its.
     * @return - if the different is big
     */
    private boolean isAdaptiveSamplingNeeded(List<Color> colors) {
        double different = 0;
        for (Color c1 : colors) {
            for (Color c2 : colors) {
                if (!(c1.equals(c2))) {
                    different += c1.colorDifference(c2);
                }
            }
        }
        if (different > differentColors)
            return true;
        else
            return false;
    }

    /**
     * Adds a grid to the imageWriter
     *
     * @param interval - How many pixels are there in each square in the grid
     * @param color    - the color of the grid
     */
    public Camera printGrid(int interval, Color color) {
        for (int x = 0; x < imageWriter.getNx(); x++) {
            for (int y = 0; y < imageWriter.getNy(); y++) {
                if (x % interval == 0 || y % interval == 0)
                    imageWriter.writePixel(x, y, color);
            }
        }
        imageWriter.writeToImage();
        return this;
    }

    /**
     * Runs the image maker of writerImage
     */
    public void writeToImage() {
        imageWriter.writeToImage();
    }

    /**
     * the class that build the camera in Builder Design Pattern
     */
    public static class Builder {
        private final Camera camera;

        // antialiasing

        /**
         * setter to minRaysPerPixel
         *
         * @param minRaysPerPixel to put in the field minRaysPerPixel
         * @return this
         */
        public Builder setMinRaysPerPixel(int minRaysPerPixel) {
            camera.minRaysPerPixel = minRaysPerPixel;
            return this;
        }

        /**
         * setter to maxRaysPerPixel
         *
         * @param maxRaysPerPixel to put in the field maxRaysPerPixel
         * @return this
         */
        public Builder setMaxRaysPerPixel(int maxRaysPerPixel) {
            camera.maxRaysPerPixel = maxRaysPerPixel;
            return this;
        }

        /**
         * setter to differentColors
         *
         * @param differentColors to put in the field differentColors
         * @return this
         */
        public Builder setDifferentColors(int differentColors) {
            camera.differentColors = differentColors;
            return this;
        }

        /**
         * Initializing the camera
         */
        public Builder() {
            camera = new Camera();
        }

        /**
         * @param camera - Initializing the camera in this parameter
         */
        public Builder(Camera camera) {
            this.camera = camera;
        }

        /**
         * Initializing the location of the camera
         *
         * @param p0 the location to initializing
         * @return the object of the build
         */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * Initializing the direction
         *
         * @param vTo the vector go towards the view plane
         * @param vUp the vector go towards the top
         * @return the object of the build
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (!(Util.isZero(vTo.dotProduct(vUp)))) {
                throw new IllegalArgumentException("Error: vTo and vUp are not vertical");
            }
            if ((vTo.normalize()).equals(vUp.normalize())) {
                throw new IllegalArgumentException("Error: vTo and vUp are parallels");
            }
            if (((vTo.normalize()).scale(-1)).equals(vUp.normalize())) {
                throw new IllegalArgumentException("Error: vTo and vUp are parallels");
            }
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();

            return this;
        }

        /**
         * Initializing the size of the view plane
         *
         * @param width  of the view plane
         * @param height of the view plane
         * @return the object of the build
         */
        public Builder setVpSize(double width, double height) {
            if (Util.isZero(width) || Util.isZero(height) || width < 0 || height < 0) {
                throw new IllegalArgumentException("Error: width and height are zero or negative");
            }

            camera.viewPlaneWidth = width;
            camera.viewPlaneHeight = height;

            return this;
        }

        /**
         * Initializing the distance from the camera to the view plane
         *
         * @param distance from the camera to the view plane
         * @return the object of the build
         */
        public Builder setVpDistance(double distance) {
            camera.viewPlaneDistance = distance;

            return this;
        }

        /**
         * Initializing the imageWriter
         *
         * @param imageWriter to initializing the field imageWriter of the camera
         * @return the object of the build
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;

            return this;
        }

        /**
         * Initializing the rayTracer
         *
         * @param rayTracer to initializing the field rayTracer of the camera
         * @return the object of the build
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;

            return this;
        }

        /**
         * Calls all functions within the class to create the camera object
         *
         * @return camera
         */
        public Camera build() {
            final String misRender = "Error: Missing render data";
            final String nameClass = "Camera";

            if (camera.p0 == null) {
                throw new MissingResourceException(misRender, nameClass, "p0");
            }
            if (camera.vTo == null) {
                throw new MissingResourceException(misRender, nameClass, "vTo");
            }
            if (camera.vUp == null) {
                throw new MissingResourceException(misRender, nameClass, "vUp");
            }
            camera.vRight = (camera.vTo.crossProduct(camera.vUp)).normalize();

            if (camera.viewPlaneHeight == 0.0) {
                throw new MissingResourceException(misRender, nameClass, "viewPlaneHeight");
            }
            if (camera.viewPlaneWidth == 0.0) {
                throw new MissingResourceException(misRender, nameClass, "viewPlaneWidth");
            }
            if (camera.viewPlaneDistance == 0.0) {
                throw new MissingResourceException(misRender, nameClass, "viewPlaneDistance");
            }
            if (camera.imageWriter == null) {
                throw new MissingResourceException(misRender, nameClass, "imageWriter");
            }
            if (camera.rayTracer == null) {
                throw new MissingResourceException(misRender, nameClass, "rayTracer");
            }

            return (Camera) camera.clone();
        }
    }
}
