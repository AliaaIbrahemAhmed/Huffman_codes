package Decoder;

import Input.Input;

public class DecoderNode {
    private Input input;
    private DecoderNode left;
    private DecoderNode right;
    private DecoderNode parent;

    public DecoderNode(Input input, DecoderNode parent) {
        this.input = input;
        this.parent = parent;
    }

    public Input getInput() {
        return input;
    }

    public DecoderNode getParent() {
        return parent;
    }

    public void setParent(DecoderNode parent) {
        this.parent = parent;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public DecoderNode getLeft() {
        return left;
    }

    public void setLeft(DecoderNode left) {
        this.left = left;
    }

    public DecoderNode getRight() {
        return right;
    }

    public void setRight(DecoderNode right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }
}
