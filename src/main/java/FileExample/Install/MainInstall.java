package FileExample.Install;

import java.io.File;

public class MainInstall {
    public static void main(String[] args) {

        String path = ("/Users/denis/FileExample/Games");
        Files files = new Files();
        files.createFolder(path + File.separator, "src");
        files.createFolder(path + File.separator, "rec");
        files.createFolder(path + File.separator, "savegames");
        files.createFolder(path + File.separator, "temp");
        files.createFolder(path + File.separator, "src/main");
        files.createFolder(path + File.separator, "src/test");
        files.createFile(path + File.separator + "src/main", "Main.java");
        files.createFile(path + File.separator + "src/main", "Utils.java");
        files.createFolder(path + File.separator, "rec/drawables");
        files.createFolder(path + File.separator, "rec/vectors");
        files.createFolder(path + File.separator, "rec/icons");
        files.createFile(path + File.separator + "temp", "temp.txt");
        files.writeFile(path + File.separator + "temp/temp.txt");
    }

}