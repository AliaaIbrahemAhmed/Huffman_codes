package Encoder.Output;

import Input.Input;
import Encoder.EncoderNode;

public class OutputTreeTraversal {

    private static String preorderTraverse(EncoderNode encoderNode) {
        if (encoderNode.isLeaf()) return "1" + getBinaryOfSingleInput(encoderNode.getValue());
        else return "0" + preorderTraverse(encoderNode.getLeft()) + preorderTraverse(encoderNode.getRight());
    }

    private static String getBinaryOfSingleInput(Input input) {
        byte[] bytes = input.getBytes();
        StringBuilder res = new StringBuilder();
        for (byte b : bytes) {
            if (b < 0) {
                String bin = Integer.toBinaryString(b), binDash;
                binDash = bin.substring(24, 32);
                res.append(binDash);
            } else res.append(getFullByte(Integer.toBinaryString(b)));
        }
        return res.toString();
    }

    private static String getFullByte(String bin) {
        StringBuilder binBuilder = new StringBuilder(bin);
        while (binBuilder.length() < 8) {
            binBuilder.insert(0, "0");
        }
        bin = binBuilder.toString();
        return bin;
    }

    public static String generatePreOrderTraversal(EncoderNode root) {
        return preorderTraverse(root);
    }

}
