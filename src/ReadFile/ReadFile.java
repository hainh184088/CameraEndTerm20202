package ReadFile;// Import package cần thiết
import Room.Room;

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
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
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