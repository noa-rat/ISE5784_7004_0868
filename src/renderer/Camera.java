package renderer;

import primitives.*;

import java.util.MissingResourceException;

/**
 * Use Builder Design Pattern
 */
public class Camera implements Cloneable{
    // Camera location
    private Point p0;

    private Vector vTo;
    private Vector vUp;
    private Vector vRight;

    private double viewPlaneHeight = 0.0;
    private double viewPlaneWidth = 0.0;
    private double viewPlaneDistance = 0.0;

    /**
     *
     * @return the Camera location
     */
    public Point getP0() {
        return p0;
    }

    /**
     *
     * @return vTo, the vector go towards the view plane
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     *
     * @return vUp, the vector go towards the top
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     *
     * @return vRight, the vector go to the right
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     *
     * @return the height of the view plane
     */
    public double getViewPlaneHeight() {
        return viewPlaneHeight;
    }

    /**
     *
     * @return the width of the view plane
     */
    public double getViewPlaneWidth() {
        return viewPlaneWidth;
    }

    /**
     *
     * @return the distance from the camera to the view plane
     */
    public double getViewPlaneDistance() {
        return viewPlaneDistance;
    }

    private Camera() {}

    /**
     *
     * @return the object of the build
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * construct the ray from the camera to the view plane
     * @param nX - number of the rows in the view plane
     * @param nY - number of the columns in the view plane
     * @param j - index column of the specific pixel
     * @param i - index row of the specific pixel
     * @return the ray from the camera to the pixel in the view plane
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }

    /**
     * the class that build the camera in Builder Design Pattern
     */
    public static class Builder {
        private final Camera camera;

        /**
         * Initializing the camera
         */
        public Builder() {
            camera = new Camera();
        }

        /**
         *
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
            camera.vRight = vTo.crossProduct(vUp);

            return this;
        }

        /**
         * Initializing the size of the view plane
         *
         * @param width of the view plane
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
            if (camera.vRight == null) {
                throw new MissingResourceException(misRender, nameClass, "vRight");
            }
            if (camera.viewPlaneHeight == 0.0) {
                throw new MissingResourceException(misRender, nameClass, "viewPlaneHeight");
            }
            if (camera.viewPlaneWidth == 0.0) {
                throw new MissingResourceException(misRender, nameClass, "viewPlaneWidth");
            }
            if (camera.viewPlaneDistance == 0.0) {
                throw new MissingResourceException(misRender, nameClass, "viewPlaneDistance");
            }
        }
    }
}
