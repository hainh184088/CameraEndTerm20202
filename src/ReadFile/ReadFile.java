package ReadFile;// Import package cần thiết

import Camera.Camera;
import Room.Room;
import Object.Object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class ReadFile {
    public static void readFile(Room room) throws IOException {
        JFileChooser c = new JFileChooser();
        String filename = "";
        String dir = "";
        int rVal = c.showOpenDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename = c.getSelectedFile().getName();
            dir = c.getCurrentDirectory().toString();
        }
        String url = dir + "/" + filename;

        // Đọc dữ liệu từ File với BufferedReader
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;

        try {
            int indexLine = 1;
            int indexColumn = 0;
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while (true) {
                StringBuilder subString = new StringBuilder();
                line = bufferedReader.readLine();
                if (line == null)
                    break;
                System.out.println(line);
                line = line.replace(',', ' ');
                line = line.replaceAll("[{]", "");
                line = line.replaceAll("[}]", "");
                Object object = new Object();
                Camera camera = new Camera();
                if (line.equals("//")) {
                    indexLine += 1;
                } else for (char character : line.toCharArray()) {
                    if (Character.isDigit(character)) {
                        subString.append(character);
                    } else if (Character.isWhitespace(character)) {
                        indexColumn += 1;
                        double value = Double.valueOf(subString.toString());
                        switch (indexLine) {
                            case 1:
                                switch (indexColumn) {
                                    case 1:
                                        room.setRoomLength((int) value);
                                        break;
                                    case 2:
                                        room.setRoomWidth((int) value);
                                        break;
                                    case 3:
                                        room.setRoomHeight((int) value);
                                        break;
                                }
                                break;
                            case 2:

                                switch (indexColumn) {
                                    case 1:
                                        object.getA().setX(value);
                                        break;
                                    case 2:
                                        object.getA().setY(value);
                                        break;
                                    case 3:
                                        object.getA().setZ(value);
                                        break;
                                    case 4:
                                        object.getB().setX(value);
                                        break;
                                    case 5:
                                        object.getB().setY(value);
                                        break;
                                    case 6:
                                        object.getB().setZ(value);
                                        break;
                                    case 7:
                                        object.setObjectLength((int) value);
                                        break;
                                    case 8:
                                        object.setObjectHeight((int) value);
                                        room.addObject(object);
                                        break;

                                }
                                break;
                            case 3:

                                switch (indexColumn) {
                                    case 1:
                                        camera.setxCamera((int) value);
                                        break;
                                    case 2:
                                        camera.setyCamera((int) value);
                                        break;
                                    case 3:
                                        camera.setzCamera((int) value);
                                        break;
                                    case 4:
                                        camera.setAngleHeight((int) value);
                                        break;
                                    case 5:
                                        camera.setAngleWidth((int) value);
                                        break;
                                    case 6:
                                        camera.setRange((int) value);
                                        room.addCamera(camera);
                                        break;

                                }
                                break;
                        }
                        subString.setLength(0);
                    }
                }
                indexColumn = 0;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFile.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFile.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadFile.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}