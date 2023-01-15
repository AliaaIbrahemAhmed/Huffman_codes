package Encoder.Output;

import java.util.ArrayList;

public class ChunkFormatter {

    private final ArrayList<Byte> bytesArrayList;
    byte lastAdded;


    public ChunkFormatter() {
        this.bytesArrayList = new ArrayList<>();
    }

    public void addByte(byte b) {
        bytesArrayList.add(b);
        lastAdded = b;
    }

    public ArrayList<Byte> getBytesArrayList() {
        return bytesArrayList;
    }

    public int getChunkSize() {
        return bytesArrayList.size();
    }

    public byte[] getBytes() {
        return getArrayFromArrayList();
    }

    private byte[] getArrayFromArrayList() {
        byte[] bytes = new byte[bytesArrayList.size()];
        int c = 0;
        for (Byte b : bytesArrayList) {
            bytes[c++] = b;
        }
        return bytes;
    }

}
