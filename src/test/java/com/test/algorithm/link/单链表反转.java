package com.test.algorithm.link;


import lombok.Data;
import org.junit.Test;

public class 单链表反转 {

    @Data
    static class Node {
        private boolean head;
        private Integer data;
        private Node next;

    }

    @Test
    public void testInverse() {
        Integer[] arr = new Integer[]{
                3, 4, 5, 6, 7, 8, 9
        };

        inverse(arr);
        headInsert(arr);
        Integer[] arr2 = new Integer[]{
                1
        };
        headInsert(arr2);
        inverse(arr2);
        Integer[] arr3 = new Integer[]{
                1, 2
        };

        inverse(arr3);
        headInsert(arr3);
    }

    private void headInsert(Integer[] arr) {
        Node head = toNode(arr);

        System.out.println("=========origin==========");
        printNode(head);

        Node inverse = headInsert(head);

        System.out.println("---------head insert--------");
        printNode(inverse);

    }

    private Node headInsert(Node head) {
        if (head.next == null) {
            return head;
        }
        Node newHead = new Node();
        newHead.setData(head.getData());
        while (head.next != null) {
            Node tmp = head.next;
            head.next = head.next.next;

            tmp.next = newHead.next;
            newHead.next = tmp;
        }
        return newHead;
    }

    private void inverse(Integer[] arr) {
        Node head = toNode(arr);

        System.out.println("=========origin==========");
        printNode(head);

        Node inverse = inverse(head);

        System.out.println("---------inverse--------");
        printNode(inverse);
    }

    private Node toNode(Integer[] arr) {
        Node head = new Node();
        head.setData(-1);
        head.setHead(true);
        Node tail = head;
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node();
            node.setData(arr[i]);
            node.setNext(null);
            tail.next = node;
            tail = node;
        }
        return head;
    }

    private Node inverse(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = head.next;
        if (pre == null) {
            return head;
        }
        Node pCur = pre.next;
        if (pCur == null) {
            pre.next = head;
            head.next = null;
            return pre;
        }

        while (pCur != null) {
            pre.next = pCur.next;
            pCur.next = head.next;
            head.next = pCur;
            pCur = pre.next;
        }
        return head;
    }

    private static void printNode(Node head) {
        Node p = head;
        while (p != null) {
            System.out.print(p.getData());
            System.out.print("->");
            p = p.next;
        }
        System.out.println();
    }
}
