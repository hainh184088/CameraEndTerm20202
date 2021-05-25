package camera;

public class Camera {
    private int xCamera, yCamera, zCamera;
    private int angleWidth;
    private int angleHeight;
    private int range;

    public Camera(int x, int y, int z, int angleWidth, int angleHeight, int range) {
        this.xCamera = x;
        this.yCamera = y;
        this.zCamera = z;
        this.angleWidth = angleWidth;
        this.angleHeight = angleHeight;
        this.range = range;
    }

    public int getxCamera() {
        return xCamera;
    }

    public int getyCamera() {
        return yCamera;
    }

    public int getzCamera() {
        return zCamera;
    }

    public int getAngleWidth() {
        return angleWidth;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setAngleWidth(int angleWidth) {
        this.angleWidth = angleWidth;
    }

    public void setzCamera(int zCamera) {
        this.zCamera = zCamera;
    }

    public void setyCamera(int yCamera) {
        this.yCamera = yCamera;
    }

    public void setxCamera(int xCamera) {
        this.xCamera = xCamera;
    }

    public int getAngleHeight() {
        return angleHeight;
    }

    public void setAngleHeight(int angleHeight) {
        this.angleHeight = angleHeight;
    }
}
