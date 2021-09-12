package com.senla.fileworker;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWorker {
    private String path;

    public FileWorker(String path) {
        this.path = path;
    }

    public void writeString(String data, boolean bool) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, bool))) {
            writer.write(data);
            writer.append(" ");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public String getString(String path) throws IOException {
        StringBuffer string = new StringBuffer();
        Path path1 = FileSystems.getDefault().getPath(path);
        Files.lines(path1).forEach(k -> string.append(k).append(" "));
        return string.toString();
    }
}
