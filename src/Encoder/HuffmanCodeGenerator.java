package Encoder;

import Encoder.Output.EncodedTreeInfo;
import Input.*;
import Encoder.Output.OutputTreeTraversal;
import Encoder.Output.OutputWriter;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    private final int numOfBytes;

    public HuffmanCodeGenerator(int numOfBytes) {
        this.numOfBytes = numOfBytes;
    }

    public HashMap<Input, Integer> generateFrequencies(Input[] inputs) {
        HashMap<Input, Integer> frequencies = new HashMap<Input, Integer>();
        for (Input b : inputs) {
            frequencies.merge(b, 1, Integer::sum);
        }
        return frequencies;
    }

    public PriorityQueue<EncoderNode> generatePriorityQ(HashMap<Input, Integer> frequencies) {
        PriorityQueue<EncoderNode> pQ = new PriorityQueue<>();
        for (HashMap.Entry<Input, Integer> entry : frequencies.entrySet()) {
            pQ.add(new EncoderNode(entry.getValue(), entry.getKey()));
        }
        return pQ;
    }

    public EncoderNode generateTheTree(PriorityQueue<EncoderNode> pQ) {
        while (pQ.size() > 1) {
            EncoderNode encoderNode = new EncoderNode();
            EncoderNode left = pQ.poll();
            EncoderNode right = pQ.poll();
            encoderNode.setFreq(left.getFreq() + right.getFreq());
            encoderNode.setLeft(left);
            encoderNode.setRight(right);
            pQ.add(encoderNode);
        }
        return pQ.poll();
    }

    public void traverse(EncoderNode encoderNode, HashMap<Input, String> codes) {
        if (encoderNode.getLeft() == null && encoderNode.getRight() == null) {
            codes.put(encoderNode.getValue(), encoderNode.getBinaryRepresentation());
            return;
        }
        String rep = encoderNode.getBinaryRepresentation();
        encoderNode.getRight().setBinaryRepresentation(rep);
        encoderNode.getRight().addOneToRep();
        encoderNode.getLeft().setBinaryRepresentation(rep);
        encoderNode.getLeft().addZeroToRep();
        traverse(encoderNode.getLeft(), codes);
        traverse(encoderNode.getRight(), codes);
    }


public void compressCertainBytes(OutputWriter outputWriter, HashMap<Input, String> codes, Input[] inputs, String outputTreeTraversal) {
    EncodedTreeInfo encodedTreeInfo = outputWriter.parseStringToBytes(outputTreeTraversal);
    outputWriter.write(encodedTreeInfo.getTreeBytes().length);
    for (Input b : inputs) {
        outputWriter.add(codes.get(b));
    }
    int dataLeadingZeroes = outputWriter.close();
    outputWriter.write(outputWriter.getNumberOfBytes());
    outputWriter.write(encodedTreeInfo.getLeadingZeroesOfLastByte());
    outputWriter.write(dataLeadingZeroes);
    outputWriter.writeBytes(encodedTreeInfo.getTreeBytes());
    outputWriter.writeToFile();
}



    public void chunksLoop(FileInputStream fileInputStream, OutputWriter outputWriter) throws IOException {
        int chunkLength = 5000000;
        Input[] inputs = InputReader.read(this.numOfBytes, fileInputStream, chunkLength);
        while (inputs.length != 0) {
            EncoderNode root = generateTheTree(generatePriorityQ(generateFrequencies(inputs)));
            HashMap<Input, String> codes = new HashMap<>();
            traverse(root, codes);
            compressCertainBytes(outputWriter, codes, inputs, OutputTreeTraversal.generatePreOrderTraversal(root));
            inputs = InputReader.read(this.numOfBytes, fileInputStream, chunkLength);
        }
    }


    public void compress(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        OutputWriter outputWriter = new OutputWriter(bufferedOutputStream);
        outputWriter.write(this.numOfBytes);
        chunksLoop(fileInputStream, outputWriter);
        bufferedOutputStream.flush();
        fileInputStream.close();
        fileOutputStream.close();
    }


}
/*
  public void compressCertainBytes(OutputWriter outputWriter, HashMap<Input, String> codes, Input[] inputs, String outputTreeTraversal) {
        long start = System.currentTimeMillis();
        outputWriter.write(outputTreeTraversal.length());
        long end = System.currentTimeMillis();
        System.out.println("write traversal length + " + (end - start));
        start = System.currentTimeMillis();
        outputWriter.add(outputTreeTraversal);
        end = System.currentTimeMillis();
        System.out.println("add tree traversal header + " + (end - start));
        start = System.currentTimeMillis();
        for (Input b : inputs) {
            outputWriter.add(codes.get(b));
        }
        end = System.currentTimeMillis();
        System.out.println("encode + " + (end - start));
        start = System.currentTimeMillis();
        outputWriter.close();
        end = System.currentTimeMillis();
        System.out.println("close writer + " + (end - start));
        start = System.currentTimeMillis();
        outputWriter.write(outputWriter.getNumberOfBytes());
        end = System.currentTimeMillis();
        System.out.println("write num of bytes + " + (end - start));
        start = System.currentTimeMillis();
        outputWriter.writeToFile();
        end = System.currentTimeMillis();
        System.out.println("write chunk to file+ " + (end - start));
    }


    public void chunksLoop(FileInputStream fileInputStream, OutputWriter outputWriter) throws IOException {
        int chunkLength = 50000;
        long start = System.currentTimeMillis();
        Input[] inputs = InputReader.read(this.numOfBytes, fileInputStream, chunkLength);
        long end = System.currentTimeMillis();
        System.out.println("READ + " + (end - start));
        while (inputs.length != 0) {
            System.out.println(inputs.length);
            start = System.currentTimeMillis();
            EncoderNode root = generateTheTree(generatePriorityQ(generateFrequencies(inputs)));
            end = System.currentTimeMillis();
            System.out.println("Tree + PQ + FREQ : " + (end - start));
            start = System.currentTimeMillis();
            HashMap<Input, String> codes = new HashMap<>();
            traverse(root, codes);
            end = System.currentTimeMillis();
            System.out.println("Tree TRAVERSAL : " + (end - start));
            start = System.currentTimeMillis();
            compressCertainBytes(outputWriter, codes, inputs, OutputTreeTraversal.generatePreOrderTraversal(root));
            end = System.currentTimeMillis();
            System.out.println("CHUNK COMPRESSION : " + (end - start));
            start = System.currentTimeMillis();
            inputs = InputReader.read(this.numOfBytes, fileInputStream, chunkLength);
            end = System.currentTimeMillis();
            System.out.println("READ : " + (end - start));
        }
    }
 */