package FileExample.UnSave;

import FileExample.Save.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class openZip {
    public static void main(String[] args) {
        String pathFile = "/Users/denis/FileExample/Games/savegames/zipSaveFile.zip";
        String pathFolder = "/Users/denis/FileExample/Games/savegames/";
        openZipFile(pathFile, pathFolder);
        openProgress("/Users/denis/FileExample/Games/savegames/saveOne.data");
    }

    public static void openZipFile(String pathFile, String pathFolder) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(pathFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathFolder + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String pathGame) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(pathGame);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }
}