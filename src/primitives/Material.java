package primitives;

public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;

    /**
     * Attenuation coefficient of transparency שקיפות
     */
    public Double3 kT = Double3.ZERO;
    /**
     * reflection attenuation coefficient השתקפות
     */
    public Double3 kR = Double3.ZERO;

    /**
     * setter
     *
     * @param kD to put the filed kD;
     * @return Material
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter
     *
     * @param kS to put the filed kS;
     * @return Material
     */

    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setter
     *
     * @param kD to put the filed kD;
     * @return Material
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter
     *
     * @param kS to put the filed kS;
     * @return Material
     */

    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter
     *
     * @param nShininess to put the filed nShininess;
     * @return Material
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * setter
     *
     * @param kT to put the filed kT
     * @return Material
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter
     *
     * @param kR to put the filed kR
     * @return Material
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * setter
     *
     * @param kT to put the filed kT
     * @return Material
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * setter
     *
     * @param kR to put the filed kR
     * @return Material
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }
}
