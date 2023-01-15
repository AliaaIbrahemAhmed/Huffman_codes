package Input;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class InputReader {
    /* chunk length must be divisible by n */
    public static Input[] read(int numOfBytes, FileInputStream fileInputStream, int chunkLength) throws IOException {
        int i = 0, k = 0;
        byte[] bytes = fileInputStream.readNBytes(Math.min(chunkLength, fileInputStream.available()));
        int inputSize = bytes.length / numOfBytes;
        Input[] inputs = new Input[inputSize];
        Input temp;
        while (i < bytes.length) {
            try {
                temp = new Input(numOfBytes);
                while (temp.getCounter() < numOfBytes && i < bytes.length) {
                    temp.addByte(bytes[i++]);
                }
                inputs[k++] = temp;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inputs;
    }
}
