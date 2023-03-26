package FileExample.Save;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MainSave {
    public static void main(String[] args) {
        String path = "/Users/denis/FileExample/Games/savegames/";
        GameProgress saveOne = new GameProgress(100, 10, 2, 10.0);
        GameProgress saveTwo = new GameProgress(85, 19, 7, 50.0);
        GameProgress saveThree = new GameProgress(40, 21, 11, 75.0);

        saveGames(path + "saveOne.data", saveOne);
        saveGames(path + "saveTwo.data", saveTwo);
        saveGames(path + "saveThree.data", saveThree);
        String zipPath = path + "zipSaveFile.zip";
        File file = new File(path);
        File[] allFilePath = file.listFiles(new LogFilterFilter());
        List<String> allFiles = new ArrayList<>();
        for (File files : allFilePath) {
            allFiles.add(files.getPath());
        }
        zipFiles(zipPath, allFiles);

    }

    public static void saveGames(String path, GameProgress gameProgress) {
        try (FileOutputStream fileOutS = new FileOutputStream(path);
             ObjectOutputStream objectOutS = new ObjectOutputStream(fileOutS)) {
            objectOutS.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> filePath) {
        System.out.println(filePath);
        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipPath))) {
            for (String file : filePath) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    File fileWrite = new File(file);
                    ZipEntry entry = new ZipEntry(fileWrite.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                    fileWrite.delete();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}