package Point;

import Camera.Camera;
import Object.Object;
import Room.Room;

import java.util.ArrayList;

public class Point {
    private double x;
    private double y;
    private double z;
    private int insideObject, Covered, Seen;

    public int getInsideObject() {
        return insideObject;
    }

    public void setInsideObject(int insideObject) {
        this.insideObject = insideObject;
    }

    public int getCovered() {
        return Covered;
    }

    public void setCovered(int covered) {
        Covered = covered;
    }

    public int getSeen() {
        return Seen;
    }

    public void setSeen(int seen) {
        Seen = seen;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Point() {

    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static double lengthLine(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point1.x - point2.x), 2) + Math.pow((point1.y - point2.y), 2) + Math.pow((point1.z - point2.z), 2));
    }

    public double findDistanceFromPointToLine(Point pointLine1, Point pointLine2) {
        double[] lineVector = new double[3];
        lineVector[0] = pointLine2.getX() - pointLine1.getX();
        lineVector[1] = pointLine2.getY() - pointLine1.getY();
        lineVector[2] = pointLine2.getZ() - pointLine1.getZ();
        double[] pointLine1PointVector = new double[3];
        pointLine1PointVector[0] = this.getX() - pointLine1.getX();
        pointLine1PointVector[1] = this.getY() - pointLine1.getY();
        pointLine1PointVector[2] = this.getZ() - pointLine1.getZ();
        Point numerator = new Point();
        numerator.setX(lineVector[1] * pointLine1PointVector[2] - lineVector[2] * pointLine1PointVector[1]);
        numerator.setY(lineVector[2] * pointLine1PointVector[0] - lineVector[0] * pointLine1PointVector[2]);
        numerator.setZ(lineVector[0] * pointLine1PointVector[1] - lineVector[1] * pointLine1PointVector[0]);
        Point newPoint = new Point(0, 0, 0);
        return lengthLine(numerator, newPoint) / lengthLine(pointLine1, pointLine2);
    }

    public double findDistanceFromPointToSurface(double[] equationSurface) {
        return Math.abs(this.x * equationSurface[0] + this.y * equationSurface[1] + this.z * equationSurface[2] + equationSurface[3]) / Math.sqrt(Math.pow(equationSurface[0], 2) + Math.pow(equationSurface[1], 2) + Math.pow(equationSurface[2], 2));
    }

    public double[] findPointOnSurface(Point point, Point cameraPoint, double[] equationSurface) {
        double[] lineEquation = new double[3];
        double[] result = new double[3];
        lineEquation[0] = point.x - cameraPoint.x;
        lineEquation[1] = point.y - cameraPoint.y;
        lineEquation[2] = point.z - cameraPoint.z;
        double t = -(point.x * equationSurface[0] + point.y * equationSurface[1] + point.z * equationSurface[2] + equationSurface[3]) /
                (equationSurface[0] * lineEquation[0] + equationSurface[1] * lineEquation[1] + equationSurface[2] * lineEquation[2]);
        result[0] = point.x + lineEquation[0] * t;
        result[1] = point.y + lineEquation[1] * t;
        result[2] = point.z + lineEquation[2] * t;
        return result;
    }

    public int checkPointObscurity(Point cameraPoint, Point pointOnSurface) {
        if (lengthLine(pointOnSurface, cameraPoint) > lengthLine(this, cameraPoint)) {
            System.out.println("This point is not obscured");
            return 0;
        } else return 1;
    }

    public double[] findProjectionToSurface(Point point, double[] equationSurface) {
        double result[] = new double[3];
        double t = -(point.x * equationSurface[0] + point.y * equationSurface[1] + point.z * equationSurface[2] + equationSurface[3]) /
                (Math.pow(equationSurface[0], 2) + Math.pow(equationSurface[1], 2) + Math.pow(equationSurface[2], 2));
        result[0] = point.x + equationSurface[0] * t;
        result[1] = point.y + equationSurface[1] * t;
        result[2] = point.z + equationSurface[2] * t;
        return result;
    }

    public int checkPointInsideObject(Object object) {
        double equationSurface2[] = object.getEquationVerticalSurface2();
        double equationSurface1[] = object.getEquationVerticalSurface1();
        double equationFrontSurface[] = object.getEquationFrontSurface();
        double equationBackSurface[] = object.getEquationBackSurface();

        double height1 = Math.abs(this.z - object.getB().getZ());
        double height2 = Math.abs(object.getObjectHeight() - this.z);
        double heightSurf2 = this.findDistanceFromPointToSurface(equationSurface2);
        double heightSurf1 = this.findDistanceFromPointToSurface(equationSurface1);
        double heightFront = this.findDistanceFromPointToSurface(equationFrontSurface);
        double heightBack = this.findDistanceFromPointToSurface(equationBackSurface);

        if (object.getObjectVolume() == 1.0 / 3.0 * ((height1 + height2) * (object.getObjectLength() * object.getObjectWidth()) + (heightSurf1 + heightSurf2) * (object.getObjectWidth() * object.getObjectHeight()) + (heightFront + heightBack) * (object.getObjectLength() * object.getObjectHeight()))) {
            System.out.println("This point is inside the object");
            return 1;
        } else {
            return 0;
        }
    }

    public int checkPointCovered(Camera camera, ArrayList<Object> objects) {
        Point cameraPoint = new Point(camera.getxCamera(), camera.getzCamera(), camera.getzCamera());
        int checkSurface1 = -1, checkSurface2 = -1, checkBackSurface = -1;
        int check = 0;
        for (Object object : objects) {
            double equationSurface2[] = object.getEquationVerticalSurface2();
            double equationSurface1[] = object.getEquationVerticalSurface1();
            double equationBackSurface[] = object.getEquationBackSurface();

            double[] pointSurface1 = findPointOnSurface(this, cameraPoint, equationSurface1);
            Point pointOnSurface1 = new Point(pointSurface1[0], pointSurface1[1], pointSurface1[2]);
            if (pointOnSurface1.checkPointInsideObject(object) == 1)
                checkSurface1 = checkPointObscurity(cameraPoint, pointOnSurface1);
            else checkSurface1 = 0;

            double[] pointSurface2 = findPointOnSurface(this, cameraPoint, equationSurface2);
            Point pointOnSurface2 = new Point(pointSurface2[0], pointSurface2[1], pointSurface2[2]);
            if (pointOnSurface2.checkPointInsideObject(object) == 1)
                checkSurface2 = checkPointObscurity(cameraPoint, pointOnSurface2);
            else checkSurface2 = 0;

            double[] pointBackSurface = findPointOnSurface(this, cameraPoint, equationBackSurface);
            Point pointOnBackSurface = new Point(pointBackSurface[0], pointBackSurface[1], pointBackSurface[2]);
            if (pointOnBackSurface.checkPointInsideObject(object) == 1)
                checkBackSurface = checkPointObscurity(cameraPoint, pointOnBackSurface);
            else checkBackSurface = 0;
            if ((checkBackSurface == 0) && (checkSurface1 == 0) && (checkSurface2 == 0) && (check == 0)) {
                System.out.println("The point is not covered by any objects");
                check = 0;
            } else check = 1;
        }
        return check;
    }


    public int checkPointInCameraVision(Point[] cameraSurface) {
        double n[] = new double[4];

        Point O = cameraSurface[1];
        Point A = cameraSurface[2];
        Point C = cameraSurface[3];
        Point B = new Point(O.getX() * 2 - A.getX(), O.getY() * 2 - A.getY(), O.getZ() * 2 - A.getZ());
        Point D = new Point(O.getX() * 2 - C.getX(), O.getY() * 2 - C.getY(), O.getZ() * 2 - C.getZ());
        n[0] = O.getX() - cameraSurface[0].getX();
        n[1] = O.getY() - cameraSurface[0].getY();
        n[2] = O.getZ() - cameraSurface[0].getZ();
        n[3] = -(n[0] * O.getX() + n[1] * O.getY() + n[2] * O.getZ());
        double[] pointK = this.findPointOnSurface(this, cameraSurface[0], n);

        Point K = new Point(pointK[0], pointK[1], pointK[2]);

//        double checkAD = (D.getX() - cameraSurface[2].getX()) * (this.y - cameraSurface[2].getY()) - (this.x - cameraSurface[2].getX()) * (D.getY() - cameraSurface[2].getY());
//        double checkBC = (cameraSurface[3].getX() - B.getX()) * (this.y - B.getY()) - (this.x - B.getX()) * (cameraSurface[3].getX() - B.getY());
        if (
                1.0 / 2.0 * ((K.findDistanceFromPointToLine(A, C) + K.findDistanceFromPointToLine(D, B)) * lengthLine(A, C) +
                        (K.findDistanceFromPointToLine(A, D) + K.findDistanceFromPointToLine(B, C)) * lengthLine(B, C)) ==
                        lengthLine(B, D) * lengthLine(B, C)
        ) {
            System.out.println("Point is inside camera vision");
            return 1;
        } else {
            return 0;
        }
    }

    public void checkPointStatus(Room room) {
        int checkPointInCameraVision = 0;
        int checkPointCovered = 0;
        int checkPointInsideObject = 0;
        ArrayList<Object> listObjects = room.getListObject();
        ArrayList<Point[]> listSurfaceCamera = new ArrayList<>();

        for (Camera tempCamera : room.getListCamera()) {
            listSurfaceCamera.add(room.cameraSurfacePoint(tempCamera));
            if (this.checkPointCovered(tempCamera, listObjects) == 0 && checkPointCovered == 0) {
            } else {
                checkPointCovered = 1;
            }
        }
        this.setCovered(checkPointCovered);
        for (Object tempObject : listObjects) {
            if (this.checkPointInsideObject(tempObject) == 0 && checkPointInsideObject == 0) {
            }
            else
                checkPointInsideObject = 1;
        }
        this.setInsideObject(checkPointInsideObject);
        for (Point[] temp : listSurfaceCamera) {
            if (this.checkPointInCameraVision(temp) == 0 && checkPointInCameraVision == 0) {
            } else checkPointInCameraVision = 1;
        }
        this.setSeen(checkPointInCameraVision);
    }


    public int setPointStatus() {
        if (this.getCovered() == 0 && this.getInsideObject() == 0 && this.getSeen() == 1) {
            return 0;
        } else return 1;
    }
}
