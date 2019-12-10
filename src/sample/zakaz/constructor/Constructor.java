package sample.zakaz.constructor;

public class Constructor {
    private int w;
    private int h;
    private int v;
    private  int q;
    private int f;
    private int s;

    public Constructor(int w, int h, int v, int q, int f, int s) {
        this.w = w;
        this.h = h;
        this.v = v;
        this.q = q;
        this.f = f;
        this.s = s;
    }


    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }
}
