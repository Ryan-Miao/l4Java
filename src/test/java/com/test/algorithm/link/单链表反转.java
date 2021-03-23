package com.test.algorithm.link;


import org.junit.Test;

/**
 * @author Ryan Miao
 * @see https://github.com/Ryan-Miao/l4Java/blob/master/src/test/java/com/test/algorithm/link/%E5%8D%95%E9%93%BE%E8%A1%A8%E5%8F%8D%E8%BD%AC.java
 */
public class 单链表反转 {


    @Test
    public void testInsert() {
        Node node = makeNode(3, 4, 5, 6, 7, 8, 9);
        System.out.println("--------origin--------");
        node.print();
        // insert 10 between 4 and 5
        Node p = node;
        while (p != null) {
            if (p.getData() == 4) {
                Node tmp = new Node();
                tmp.setData(10);
                tmp.setNext(p.getNext());
                p.setNext(tmp);
                break;
            }
            p = p.getNext();
        }

        System.out.println("--------inserted--------");
        node.print();
    }

    @Test
    public void deleteNode() {
        Node node = makeNode(3, 4, 5, 6, 7, 8, 9);
        System.out.println("--------origin--------");
        node.print();

        //delete 5
        Node head = node;
        Node p = node;
        while (p != null) {
            if (p.getData() == 5) {
                //if the first is 5, skip
                head.setNext(p.getNext());
                break;
            }
            Node pre = p;
            p = p.getNext();
            if (p != null && p.getData().equals(5)) {
                pre.setNext(p.getNext());
                break;
            }
        }

        System.out.println("---------deleted---------");
        head.print();
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
        String print = head.print();
        System.out.println(print);

        Node inverse = headInsert(head);

        System.out.println("---------head insert--------");
        String print1 = inverse.print();
        System.out.println(print1);

    }

    private Node headInsert(Node head) {
        if (head.getNext() == null) {
            return head;
        }

        // new node head
        final Node newHead = new Node();
        newHead.setHead(true);
        newHead.setData(head.getData());
        // pointer
        Node p = head;
        while (p.getNext() != null) {
            // 暂存取下的节点
            Node tmp = p.getNext();
            // 原来的链表指针移动到下一个
            p.setNext(p.getNext().getNext());
            // 取下的节点 指向 新链表的头节点之后
            tmp.setNext(newHead.getNext());
            // 新链表指向 插入的节点
            newHead.setNext(tmp);
        }
        return newHead;
    }

    private void inverse(Integer[] arr) {
        Node head = toNode(arr);

        System.out.println("=========origin==========");
        String print = head.print();
        System.out.println(print);

        Node inverse = inverse(head);

        System.out.println("---------inverse--------");
        String print1 = inverse.print();
        System.out.println(print1);
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
            tail.setNext(node);
            tail = node;
        }
        return head;
    }

    private Node inverse(Node head) {
        if (head == null) {
            return null;
        }
        // 左边链表的tail节点 te first node
        Node leftTail = head.getNext();
        if (leftTail == null) {
            return head;
        }

        // 当前的指针右边原始链表的第一个节点
        Node pCur = leftTail.getNext();
        if (pCur == null) {
            return head;
        }

        while (pCur != null) {
            // 左边链表tail指向 右边链表的下个节点
            leftTail.setNext(pCur.getNext());
            // 链表第一个取下来，要插入左边链表的头部，head就是左边链表的头部
            pCur.setNext(head.getNext());
            // head指向插入的节点
            head.setNext(pCur);
            // 右边链表指针移动下一个节点
            pCur = leftTail.getNext();
        }
        return head;
    }

}
