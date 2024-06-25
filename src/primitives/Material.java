package primitives;

public class Material {
    public Double3 kD=Double3.ZERO;
    public Double3 kS=Double3.ZERO;
    public int nShininess=0;

    /**
     * setter
     * @param kD to put the filde kD;
     * @return Material
     */
    public Material setkD(double kD) {
        this.kD =new Double3(kD);
        return this;
    }
    /**
     * setter
     * @param kS to put the filde kS;
     * @return Material
     */

    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setter
     * @param kD to put the filde kD;
     * @return Material
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }
    /**
     * setter
     * @param kS to put the filde kS;
     * @return Material
     */

    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }



    /**
     * setter
     * @param nShininess to put the filde nShininess;
     * @return Material
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
