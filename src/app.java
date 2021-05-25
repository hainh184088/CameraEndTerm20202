import Camera.Camera;
import Point.Point;
import ReadFile.ReadFile;
import Room.Room;
import Object.Object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class app {
    public static void main(String args[]) throws IOException {
        int choice = -1;
        double step = 1;
        double stepTimes = 1 / step;
        Scanner keyboard = new Scanner(System.in);

        Room room = new Room();
        ArrayList<ArrayList<ArrayList<Integer>>> coordinates = new ArrayList<ArrayList<ArrayList<Integer>>>();
        while (choice != 0) {
            System.out.println("Choice using menu: ");
            choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    String check = "y";
                    int length, width, height;
                    int x1, y1, z1, x2, y2, z2;
                    int objectLength, objectHeight;
                    System.out.println("Create by hand or import file(y/n): ");
                    check = keyboard.nextLine();
                    check = keyboard.nextLine();
                    if (check.equals("y")) {
                        ReadFile.readFile();
                    }
                    System.out.println("Create room: ");
                    System.out.println("Length: ");
                    length = keyboard.nextInt();
                    System.out.println("Width: ");
                    width = keyboard.nextInt();
                    System.out.println("Height: ");
                    height = keyboard.nextInt();
                    room.setRoomLength(length);
                    room.setRoomWidth(width);
                    room.setRoomHeight(height);
                    check = "y";
                    while (check.equals("y")) {
                        System.out.println("Create new object: ");
                        System.out.println("X1 coordinate: ");
                        x1 = keyboard.nextInt();
                        System.out.println("Y1 coordinate: ");
                        y1 = keyboard.nextInt();
                        System.out.println("Z1 coordinate: ");
                        z1 = keyboard.nextInt();
                        System.out.println("X2 coordinate: ");
                        x2 = keyboard.nextInt();
                        System.out.println("Y2 coordinate: ");
                        y2 = keyboard.nextInt();
                        System.out.println("Z2 coordinate: ");
                        z2 = keyboard.nextInt();
                        System.out.println("Object Length: ");
                        objectLength = keyboard.nextInt();
                        System.out.println("Object Height: ");
                        objectHeight = keyboard.nextInt();
                        Object newObject = new Object(x1, y1, z1, x2, y2, z2, objectLength, objectHeight);
                        room.addObject(newObject);
                        if (room.checkObjectValidity(newObject) == 1) ;
                        System.out.println("Do you want to create next object: ");
                        check = keyboard.nextLine();
                        check = keyboard.nextLine();
                    }
                    break;
                case 2:
                    int x, y, z;
                    int angleWidth;
                    int angleHeight;
                    int range;

                    System.out.println("Create camera: ");
                    System.out.println("x position: ");
                    x = keyboard.nextInt();
                    System.out.println("y position: ");
                    y = keyboard.nextInt();
                    System.out.println("z position: ");
                    z = keyboard.nextInt();
                    System.out.println("Angle Width: ");
                    angleWidth = keyboard.nextInt();
                    System.out.println("Angle Height: ");
                    angleHeight = keyboard.nextInt();
                    System.out.println("Range: ");
                    range = keyboard.nextInt();
                    Camera camera = new Camera(x, y, z, angleWidth, angleHeight, range);
                    if (room.checkCameraPosition(camera) == 1)
                        room.addCamera(camera);
                    break;
                case 3:
                    int xTest, yTest, zTest;
                    System.out.println("x position: ");
                    xTest = keyboard.nextInt();
                    System.out.println("y position: ");
                    yTest = keyboard.nextInt();
                    System.out.println("z position: ");
                    zTest = keyboard.nextInt();
                    Point testPoint = new Point(xTest, yTest, zTest);
                    testPoint.checkPointStatus(room);
                    System.out.println("The point status: " + testPoint.setPointStatus());
                    break;
                case 4:
                    Point newPoint = new Point(0, 0, 0);
                    for (int k = 0; k <= room.getRoomHeight(); k += step) {
                        coordinates.add(new ArrayList<ArrayList<Integer>>(room.getRoomWidth()));
                        for (int j = 0; j <= room.getRoomWidth(); j += step) {
                            coordinates.get(k).add(new ArrayList<Integer>(room.getRoomLength()));
                            for (int i = 0; i <= room.getRoomLength(); i += step) {
                                newPoint.setX(i*stepTimes);
                                newPoint.setY(j*stepTimes);
                                newPoint.setZ(k*stepTimes);
                                newPoint.setCovered(0);
                                newPoint.setSeen(0);
                                newPoint.setInsideObject(0);
                                newPoint.checkPointStatus(room);
                                coordinates.get((int) (k*stepTimes)).get((int) (j*stepTimes)).add((int) (i*stepTimes), newPoint.setPointStatus());
                            }
                        }
                    }

                    //suface vertical with x = 0
                    for (int k = 0; k <= room.getRoomHeight(); k += step) {
                        System.out.println();
                        for (int j = 0; j <= room.getRoomWidth(); j += step) {
                            System.out.print(coordinates.get((int) (k*stepTimes)).get((int) (j*stepTimes)).get(0) + " ");
                        }
                    }
                    System.out.println();
                    //surface horizontal with k = 0
                    for (int j = 0; j <= room.getRoomWidth(); j += step) {
                        System.out.println();
                        for (int i = 0; i <= room.getRoomLength(); i += step) {
                            System.out.print(coordinates.get(0).get((int) (j*stepTimes)).get((int) (i*stepTimes)) + " ");
                        }
                    }
                    break;

            }
        }
    }
}
