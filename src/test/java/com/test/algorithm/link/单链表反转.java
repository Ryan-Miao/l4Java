package com.test.algorithm.link;


import lombok.Data;
import org.junit.Test;

public class 单链表反转 {

    /**
     * @author Ryan Miao
     */
    @Data
    static class Node {
        private Boolean head;
        private Integer data;
        private Node next;

    }

    @Test
    public void testInsert() {
        Node node = makeNode(3, 4, 5, 6, 7, 8, 9);
        System.out.println("--------origin--------");
        printNode(node);
        // insert 10 between 4 and 5
        Node p = node;
        while (p != null) {
            if (p.getData() == 4) {
                Node tmp = new Node();
                tmp.setData(10);
                tmp.next = p.next;
                p.next = tmp;
                break;
            }
            p = p.next;
        }

        System.out.println("--------inserted--------");
        printNode(node);
    }

    @Test
    public void deleteNode() {
        Node node = makeNode(3, 4, 5, 6, 7, 8, 9);
        System.out.println("--------origin--------");
        printNode(node);

        //delete 5
        Node head = node;
        Node p = node;
        while (p != null) {
            if (p.getData() == 5) {
                //if the first is 5, skip
                head.next = p.next;
                break;
            }
            Node pre = p;
            p = p.next;
            if (p != null && p.getData().equals(5)) {
                pre.next = p.next;
                break;
            }
        }

        System.out.println("---------deleted---------");
        printNode(head);
    }

    private Node makeNode(Integer... arr) {
        return toNode(arr);
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

        // new node head
        final Node newHead = new Node();
        newHead.setHead(true);
        newHead.setData(head.getData());
        // pointer
        Node p = head;
        while (p.next != null) {
            // 暂存取下的节点
            Node tmp = p.next;
            // 原来的链表指针移动到下一个
           p.next = p.next.next;
            // 取下的节点 指向 新链表的头节点之后
            tmp.next = newHead.next;
            // 新链表指向 插入的节点
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
        Node p = head.next;
        while (p != null) {
            System.out.print(p.getData());
            p = p.next;
            if (p != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }
}
