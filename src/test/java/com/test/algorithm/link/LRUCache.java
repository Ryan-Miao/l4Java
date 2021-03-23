package com.test.algorithm.link;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * LRUCache
 *
 * @author Ryan
 */
@Slf4j
public class LRUCache {

    @Test
    public void testCache() {
        MyLRUCache cache = new MyLRUCache();
        for (int i = 0; i < 8; i++) {
            cache.add(i);
            cache.print();
        }

        cache.add(1);
        cache.print();
        cache.add(6);
        cache.print();
        cache.add(7);
        cache.print();
        cache.add(6);
        cache.print();
    }

    static class MyLRUCache {

        private static final int CACHE_MAX_SIZE = 4;


        private final Node cacheHead = Node.head();
        final private AtomicInteger size = new AtomicInteger(0);

        public void add(Integer data) {
            log.debug("add {}", data);
            Node node = null;

            // 1.find if exist remove
            Node firstNode = cacheHead.getNext();
            Node cur = firstNode;
            while (cur != null && !cur.isHead()) {
                Node curNext = cur.getNext();
                if (data.equals(cur.getData())) {
                    // remove
                    Node pre = cur.getPre();
                    log.debug("exist, remove {}, pre{} -> next{}", cur.getData(), pre.getData(), curNext.getData());
                    pre.setNext(curNext);
                    curNext.setPre(pre);
                    size.decrementAndGet();

                    node = cur;
                    node.setNext(null);
                    node.setPre(null);
                    break;
                }

                cur = curNext;
            }

            // if not found and full remove the last
            if (node == null) {
                node = Node.normal(data);
                if (size.get() == CACHE_MAX_SIZE) {
                    Node tail = cacheHead.getPre();
                    Node tailPre = tail.getPre();
                    tailPre.setNext(cacheHead);
                    cacheHead.setPre(tailPre);
                    size.decrementAndGet();
                }
            }

            // 2.insert to head
            if (firstNode == null) {
                cacheHead.setPre(node);
                cacheHead.setNext(node);
                node.setPre(cacheHead);
                node.setNext(cacheHead);
            } else {
                node.setNext(cacheHead.getNext());
                cacheHead.getNext().setPre(node);
                cacheHead.setNext(node);
                node.setPre(cacheHead);
            }

            size.incrementAndGet();
        }

        public void print() {
            String print = cacheHead.print();
            log.debug("print: {}", print);
        }
    }
}
