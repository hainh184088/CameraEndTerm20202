package Room;

import java.util.ArrayList;

import Camera.Camera;
import Object.Object;
import Point.Point;

public class Room {
    private int roomLength;
    private int roomWidth;
    private int roomHeight;
    private ArrayList<Object> listObject = new ArrayList<Object>();
    private ArrayList<Camera> listCamera = new ArrayList<Camera>();

    public void addCamera(Camera camera) {
        listCamera.add(camera);
    }

    public void addObject(Object object) {
        listObject.add(object);
    }

    public int getRoomLength() {
        return roomLength;
    }

    public void setRoomLength(int roomLength) {
        this.roomLength = roomLength;
    }


    public int getRoomHeight() {
        return roomHeight;
    }

    public void setRoomHeight(int roomHeight) {
        this.roomHeight = roomHeight;
    }

    public ArrayList<Object> getListObject() {
        return listObject;
    }

    public ArrayList<Camera> getListCamera() {
        return listCamera;
    }

    public int getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(int roomWidth) {
        this.roomWidth = roomWidth;
    }

    public int checkObjectInsideRoom(Object checking) {
        double[] c = checking.findC();
        double[] d = checking.findD();
        Point cPoint = new Point(c[0], c[1], c[2]);
        Point aPoint = new Point(checking.getA().getX(), checking.getA().getY(), checking.getA().getZ());
        if (
                (checking.getA().getX() > this.roomLength) ||
                        (checking.getA().getX() < 0) ||
                        (checking.getB().getX() > this.roomLength) ||
                        (checking.getB().getX() < 0) ||
                        (c[0] > this.roomLength) ||
                        (c[0] < 0) ||
                        (d[0] > this.roomLength) ||
                        (d[0] < 0) ||

                        (checking.getA().getY() > this.roomWidth) ||
                        (checking.getA().getY() < 0) ||
                        (checking.getB().getY() > this.roomWidth) ||
                        (checking.getB().getY() < 0) ||
                        (c[1] > this.roomWidth) ||
                        (c[1] < 0) ||
                        (d[1] > this.roomWidth) ||
                        (d[1] < 0) ||

                        ((checking.getA().getZ() + checking.getObjectHeight()) > this.roomHeight) ||
                        (checking.getA().getZ() < 0)

        ) {
            System.out.println("Object is not inside the room");
            return 0;
        } else if (Point.lengthLine(cPoint, aPoint) == checking.getObjectLength())
            return 1;
        else {
            System.out.println("Something is wrong with the object");
            return 0;
        }
    }

    public int checkObjectValidity(Object checking) {
        int check;

        if (checkObjectInsideRoom(checking) == 1) {
            System.out.println("The object is in the room");
            check = 1;
        } else {
            System.out.println("Object parts are out of the room");
            check = 0;
        }
        return check;
    }

    public int checkCameraPosition(Camera camera) {
        int check = 0;
        if (
                (camera.getxCamera() <= this.getRoomLength()) &&
                        (camera.getyCamera() <= this.getRoomWidth()) &&
                        (camera.getzCamera() <= this.getRoomHeight())
        ) {
            System.out.println("Camera is in the room");
            if (
                    ((camera.getxCamera() == 0) ||
                            (camera.getxCamera() == this.getRoomLength()) ||
                            (camera.getyCamera() == 0) ||
                            (camera.getyCamera() == this.getRoomWidth()) ||
                            (camera.getzCamera() == this.getRoomHeight())
                    ) &&
                            (camera.getzCamera() != 0)
            ) {
                System.out.println("Camera is on the wall or celling");
                if (!((camera.getxCamera() == 0 || (camera.getxCamera() == this.getRoomLength())) &&
                        ((camera.getyCamera() == 0) || (camera.getyCamera() == this.getRoomWidth()))))
                    if ((camera.getAngleWidth() <= 90) && (camera.getAngleHeight() <= 90)) {
                        System.out.println("Camera angle is suitable");
                        if (camera.getRange() <= 100) {
                            System.out.println("Camera range is suitable");
                            check = 1;
                        } else {
                            System.out.println("Camera range is further than 100");
                            check = 0;
                        }
                    } else {
                        System.out.println("Camera angle is bigger than 90");
                        check = 0;
                    }
            } else {
                System.out.println("Camera is not on the wall or celling");
                check = 0;
            }
        } else {
            System.out.println("Camera is not in the room");
            check = 0;
        }
        return check;
    }

    public int checkObjectPlacement(Object temp) {
        int check = 0;
        double result[] = new double[3];
        Point aTemp = new Point(temp.getA().getX(), temp.getA().getY(), temp.getA().getZ());
        Point bTemp = new Point(temp.getB().getX(), temp.getB().getY(), temp.getB().getZ());
        for (Object object : this.listObject) {
            if ((object.getA().getZ() + object.getObjectHeight()) == temp.getA().getZ()) {
                System.out.println("This object may be on another object");
                check = 1;
            }
//            else
//            if(check == 1)
//                if(Surface.checkPoinOnSurface(aTemp, ){
//
//                }
        }
        return check;
    }

    public Point[] cameraSurfacePoint(Camera camera) {
        double width = 2.0 * Math.tan(Math.toRadians(camera.getAngleWidth())/2.0) * camera.getRange();

        double height = 2.0 * Math.tan(Math.toRadians(camera.getAngleHeight())/2.0) * camera.getRange();
//        System.out.println("Width" + width + "Height" + height);
        Point cameraPoint = new Point(camera.getxCamera(), camera.getyCamera(), camera.getzCamera());
        Point O = new Point();
        Point A = new Point();
        Point C = new Point();
        Point surfacePoint[] = {cameraPoint, O, A, C};
        if (!((camera.getxCamera() == 0 || camera.getxCamera() == this.getRoomLength()) && (camera.getyCamera() == 0 || camera.getyCamera() == this.getRoomWidth())))
            if (camera.getxCamera() == 0 || camera.getxCamera() == this.getRoomLength()) {
                O.setZ(camera.getzCamera());
                O.setY(camera.getyCamera());
                O.setX(camera.getxCamera() + camera.getRange());
                A.setX(O.getX());
                A.setY(O.getY() + 1.0 / 2.0 * width);
                A.setZ(O.getZ() - 1.0 / 2.0 * height);
                C.setX(O.getX());
                C.setY(O.getY() - 1.0 / 2.0 * width);
                C.setZ(A.getZ());
            } else if (camera.getyCamera() == 0 || camera.getyCamera() == this.getRoomWidth()) {
                O.setZ(camera.getzCamera());
                O.setX(camera.getxCamera());
                O.setY(camera.getyCamera() + camera.getRange());
                A.setX(O.getX() - 1.0 / 2.0 * width);
                A.setY(O.getY());
                A.setZ(O.getZ() - 1.0 / 2.0 * height);
                C.setX(O.getX() - 1.0 / 2.0 * width);
                C.setY(A.getY());
                C.setZ(O.getZ() + 1.0 / 2.0 * height);
            } else if (camera.getzCamera() == this.getRoomHeight()) {
                O.setY(camera.getyCamera());
                O.setX(camera.getxCamera());
                O.setZ(camera.getzCamera() - camera.getRange());
                A.setX(camera.getxCamera() + 1.0 / 2.0 * width);
                A.setY(camera.getyCamera() + 1.0 / 2.0 * height);
                A.setZ(O.getZ());
                C.setX(camera.getxCamera() - 1.0 / 2.0 * width);
                C.setY(A.getY());
                C.setZ(A.getZ());
            }
        return surfacePoint;
    }

}