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
        cache.add(1);
        cache.add(1);
        cache.add(2);
        for (int i = 0; i < 4; i++) {
            cache.add(i);
        }

        cache.add(1);
        cache.add(2);
        cache.add(3);
        cache.add(4);
        cache.add(4);
    }

    static class MyLRUCache {

        private static final int CACHE_MAX_SIZE = 4;


        private final Node cacheHead = Node.head();
        private final AtomicInteger size = new AtomicInteger(0);

        public void add(Integer data) {
            log.debug("add {}", data);
            //要插入的node
            Node node = null;

            // 1.find if exist remove
            Node firstNode = cacheHead.getNext();
            Node cur = firstNode;
            while (cur != null && !cur.isHead()) {
                Node curNext = cur.getNext();
                if (data.equals(cur.getData())) {
                    // remove
                    Node pre = cur.getPre();
                    log.debug("exist, remove {}, pre {} -> next {}", cur.getData(), pre.getData(), curNext.getData());
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
            if (log.isDebugEnabled()) {
                log.debug("current cache: {}", cacheHead.print());
            }
        }

    }
}
