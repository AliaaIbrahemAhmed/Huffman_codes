package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO {
    public static String getOutputFileStreamForCompression(String inputFilePath, int n) {
        File inputFile = new File(inputFilePath);
        String directory = inputFile.getParent();
        String fileName = inputFile.getName();
        String outputFilePath = directory + "/19016031." + n + "." + fileName + ".hc";
        FileOutputStream fileOutputStream = null;
        return outputFilePath;
    }

    public static FileOutputStream getOutputFileStreamForDecompression(String inputFilePath) {
        File inputFile = new File(inputFilePath);
        String directory = inputFile.getParent();
        String fileName = inputFile.getName();
        String outputFilePath = directory + "/extracted." + fileName.substring(0, fileName.length() - 3);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputFilePath);
        } catch (IOException ioException) {
            System.out.println("Error Creating File");
            ioException.printStackTrace();
        }
        return fileOutputStream;
    }

}
