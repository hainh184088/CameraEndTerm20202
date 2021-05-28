package Object;

import Point.Point;

public class Object {
    private Point a = new Point();
    private Point b = new Point();
    private int objectLength;
    private int objectHeight;

    public Object() {
    }

    public Object(int x1, int y1, int z1, int x2, int y2, int z2, int objectLength, int objectHeight) {
        this.a.setX(x1);
        this.a.setY(y1);
        this.a.setZ(z1);
        this.b.setX(x2);
        this.b.setY(y2);
        this.b.setZ(z2);
        this.objectHeight = objectHeight;
        this.objectLength = objectLength;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }

    public int getObjectLength() {
        return objectLength;
    }

    public void setObjectLength(int objectLength) {
        this.objectLength = objectLength;
    }

    public double getObjectWidth() {
        double objectWidth = Math.sqrt(Math.pow(this.b.getX() - this.a.getX(), 2) + Math.pow(this.b.getY() - this.a.getY(), 2) - Math.pow(this.getObjectLength(), 2));
        return objectWidth;
    }


    public double[] findC() {
        double result[] = new double[3];
        double m = this.b.getX() - this.a.getX();
        double n = this.b.getY() - this.a.getY();
        double a = 1 + Math.pow(m, 2) / Math.pow(n, 2);
        double b = Math.pow(this.getObjectLength(), 2) * m / Math.pow(n, 2);
        double c = Math.pow(this.getObjectLength(), 4) / Math.pow(n, 2) - Math.pow(this.getObjectLength(), 2);
        double delta = Math.pow(b, 2) - a * c;
        result[1] = this.a.getY() + (Math.sqrt(delta) - b) / a;
        result[0] = this.a.getX() + (Math.pow(this.getObjectLength(), 2) - (result[1] - this.a.getY()) * m) / n;
        result[2] = this.a.getZ();
        return result;
    }

    public double[] findD() {
        double c[] = this.findC();
        double result[] = new double[3];
        result[0] = this.b.getX() - (c[0] - this.a.getX());
        result[1] = this.b.getY() - (c[1] - this.a.getY());
        result[2] = this.a.getZ() + this.getObjectHeight();
        return result;
    }

    public double[] getEquationVerticalSurface2() {
        double[] cPoint = this.findC();
        double[] result = new double[4];
        result[0] = cPoint[0] - this.a.getX();
        result[1] = cPoint[1] - this.a.getY();
        result[2] = 0;
        result[3] = -(result[0] * cPoint[0] + result[1] * cPoint[1]);
        return result;
    }

    public double[] getEquationVerticalSurface1() {
        double[] result = this.getEquationVerticalSurface2();
        result[3] = 0 - (result[0] * this.a.getX() + result[1] * this.a.getY());
        return result;
    }

    public double getObjectVolume() {
        return this.getObjectWidth() * this.getObjectLength() * this.getObjectHeight();
    }

    public double[] getEquationFrontSurface() {
        double[] result = new double[4];
        result[0] = this.b.getX() - this.findC()[0];
        result[1] = this.b.getY() - this.findC()[1];
        result[2] = 0;
        result[3] = -(result[0] * this.a.getX() + result[1] * this.a.getY());
        return result;
    }

    public double[] getEquationBackSurface() {
        double[] result = this.getEquationFrontSurface();
        result[3] = 0 - (result[0] * this.b.getX() + result[1] * this.b.getY());
        return result;
    }

    public double[] getEquationUpSurface() {
        double[] result = new double[4];
        result[0] = 0;
        result[1] = 0;
        result[2] = 1;
        result[3] = -(this.a.getZ() + this.getObjectHeight());
        return result;
    }

    public double[] getEquationDownSurface() {
        double[] result = this.getEquationUpSurface();
        result[3] = 0 - this.a.getZ();
        return result;
    }


}
