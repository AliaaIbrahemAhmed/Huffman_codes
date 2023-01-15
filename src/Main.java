import Decoder.Decoder;
import Encoder.HuffmanCodeGenerator;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

import IO.*;


/*
“I acknowledge that I am aware of the academic integrity guidelines of this course, and that I worked on this assignment independently without any unauthorized help”.
*/

public class Main {

    public static void main(String[] args) {
        if (Objects.equals(args[0], "c")) {
            String fileToBeCompressed = args[1];
            int n = Integer.parseInt(args[2]);
            String fileToBegenerated = IO.getOutputFileStreamForCompression(fileToBeCompressed, n);
            FileOutputStream fileOutputStream;
            FileInputStream fileInputStream;
            try {
                fileOutputStream = new FileOutputStream(fileToBegenerated);
                fileInputStream = new FileInputStream(fileToBeCompressed);
                long start = System.currentTimeMillis();
                HuffmanCodeGenerator huffmanCodeGenerator = new HuffmanCodeGenerator(n);
                huffmanCodeGenerator.compress(fileInputStream, fileOutputStream);
                long end = System.currentTimeMillis();
                System.out.println("Compression time = " + (end - start) / 1000  + " sec");
                File file = new File(fileToBegenerated);
                File file1 = new File(fileToBeCompressed);
                double compressionRatio = (double) file.length() /(double)  file1.length();
                System.out.println("The compressionRatio = "+compressionRatio);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            String fileToBeDecompressed = args[1];
            FileOutputStream fileOutputStream = IO.getOutputFileStreamForDecompression(fileToBeDecompressed);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            FileInputStream fileInputStream;
            try {
                long start = System.currentTimeMillis();
                fileInputStream = new FileInputStream(fileToBeDecompressed);
                Decoder decoder = new Decoder(fileInputStream, bufferedOutputStream);
                decoder.decode();
                long end = System.currentTimeMillis();
                System.out.println("Decompression time = " + (end - start) / 1000  + " sec");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}

