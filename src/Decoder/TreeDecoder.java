package Decoder;

import Encoder.EncoderNode;
import Input.Input;

public class TreeDecoder {


    public static DecoderNode decodeTree(String binaryRep, int n) {
        DecoderNode root;
        if (binaryRep.charAt(0) == '1') {
            Input i = getInput(binaryRep, 1, n);
            root = new DecoderNode(i, null);
        } else {
            root = new DecoderNode(null, null);
            traverse(root, false, binaryRep, 1, n);
        }
        return root;
    }

    /* true for right, false for left*/
    private static void traverse(DecoderNode parent, boolean dir, String binary, int i, int n) {
        if (i >= binary.length()) return;
        int newS = i + 1 + (n * 8);
        DecoderNode node;
        if (binary.charAt(i) == '0') {
            node = new DecoderNode(null, parent);
            if (dir) parent.setRight(node);
            else parent.setLeft(node);
            traverse(node, false, binary, i + 1, n);
        } else {
            node = new DecoderNode(getInput(binary, i + 1, n), parent);
            if (dir) parent.setRight(node);
            else parent.setLeft(node);
            if (newS < binary.length() && !dir) traverse(parent, true, binary, newS, n);
            if (newS < binary.length() && dir) {
                DecoderNode grandParent = parent;
                while (grandParent != null && grandParent.getRight() != null) {
                    grandParent = grandParent.getParent();
                }
                if (grandParent != null && grandParent != parent) traverse(grandParent, true, binary, newS, n);
            }
        }
    }

    /* End is exclusive */
    private static Input getInput(String binary, int start, int n) {
        Input input = new Input(n);
        for (int i = start; i < start + (n * 8); i += 8) {
            input.addByte(getByte(binary, i));
        }
        return input;
    }

    private static byte getByte(String binary, int start) {
        byte b = 0;
        for (int i = start; i < start + 8 && i < binary.length(); i++) {
            b <<= 1;
            if (binary.charAt(i) == '1') b |= 1;
        }
        return b;
    }

}
