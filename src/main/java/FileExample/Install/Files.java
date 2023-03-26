package FileExample.Install;

import java.io.*;
import java.sql.Timestamp;

public class Files {

    private static final StringBuilder logs = new StringBuilder();
    private static final Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    public void createFolder(String path, String name) {
        File file = new File(path, name);
        if (file.mkdir()) {
            logs.append(timestamp + " Папка " + name + " успешно создана\n");
        } else {
            logs.append(timestamp + " Папка " + name + " не создана\n");
        }
    }

    public void createFile(String path, String name) {
        File file = new File(path, name);
        try {
            if (file.createNewFile())
                logs.append(timestamp + " Файл " + name + " успешно создан\n");
        } catch (IOException e) {
            logs.append(timestamp + " Файл " + name + " не создан: " + e.getMessage() + "\n");
        }
    }

    public void writeFile(String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(logs.toString());
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}