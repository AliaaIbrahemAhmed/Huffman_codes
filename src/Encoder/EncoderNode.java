package Encoder;

import Input.Input;

public class EncoderNode implements Comparable<EncoderNode>  {
    private int freq;
    private Input value;
    private EncoderNode left;
    private EncoderNode right;
    private String binaryRepresentation;

    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    public void setBinaryRepresentation(String binaryRepresentation) {
        this.binaryRepresentation = binaryRepresentation;
    }

    public EncoderNode(int freq, Input value) {
        this.freq = freq;
        this.value = value;
        binaryRepresentation = "";
    }

    public boolean isLeaf() {
        return (this.left == null) && (this.right == null);
    }


    public EncoderNode() {
        binaryRepresentation = "";
    }

    public void addOneToRep() {
        binaryRepresentation = binaryRepresentation + "1";
    }

    public void addZeroToRep() {
        binaryRepresentation = binaryRepresentation + "0";
    }

    public EncoderNode getLeft() {
        return left;
    }

    public void setLeft(EncoderNode left) {
        this.left = left;
    }

    public EncoderNode getRight() {
        return right;
    }

    public void setRight(EncoderNode right) {
        this.right = right;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Input getValue() {
        return value;
    }

    @Override
    public int compareTo(EncoderNode o) {
        return Integer.compare(this.freq, o.freq);
    }
}


