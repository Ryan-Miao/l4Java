package com.test.algorithm.link;

import lombok.Data;

/**
 * node
 *
 * @author Ryan Miao
 */
@Data
public class Node {
    private boolean head = false;
    private Integer data;
    private Node next;
    private Node pre;

    public Node() {
    }

    public static Node head() {
        Node node = new Node();
        node.setHead(true);
        node.setData(-1);
        return node;
    }

    public static Node normal(Integer data) {
        Node node = new Node();
        node.setHead(false);
        node.setData(data);
        return node;
    }

    public void insert(Node node) {
        node.setNext(this.getNext());
        node.setPre(this);
        if (this.getNext() != null) {
            this.getNext().setPre(node);
        }
        this.setNext(node);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        Node p = this;
        Node theNext = p.getNext();
        if (!p.isHead()) {
            sb.append(p.getData());
            if (theNext != null) {
                sb.append("->");
            }
        }

        while (theNext != null && !theNext.isHead()) {
            sb.append(theNext.getData());
            theNext = theNext.getNext();
            if (theNext != null && !theNext.isHead()) {
                sb.append("->");
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Node h = Node.head();
        for (int i = 0; i < 5; i++) {
            h.insert(normal(i));
        }

        String print = h.print();
        System.out.println(print);
    }

}
