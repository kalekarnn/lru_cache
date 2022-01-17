/*Problem: Implement a LRU Cache:

        - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
        - int get(int key) Return the value of the key if the key exists, otherwise return -1.
        - void put(int key, int value) Update the value of the key if the key exists.
          Otherwise, add the key-value pair to the cache. If the number of keys exceeds
          the capacity from this operation, evict the least recently used key.
*/


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Cache cache = new Cache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.display();
        cache.put(4, 4);
        cache.display();
        cache.put(2, 2);
        cache.display();
    }
}

//queue -> keep track which one is latest
//map   -> store the cached data

class Cache {
    LinkedList<Integer> queue;
    Map map;
    int CACHE_SIZE;

    public Cache(int capacity) {
        this.queue = new LinkedList<>();
        this.map = new HashMap<Integer, Integer>();
        this.CACHE_SIZE = capacity;
    }

    public Integer get(int key) {

        if (map.containsKey(key)) {
            updateQueue(key);
            return (Integer) map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            updateCache(key, value, key);
        } else {
            if (queue.size() < CACHE_SIZE) {
                map.put(key, value);
                queue.push(key);
            } else {
                int last = queue.size() - 1;
                updateCache(key, value, last);
            }
        }
    }

    private void updateCache(int key, int value, int last) {
        queue.remove(last);
        queue.push(key);
        map.put(key, value);
    }

    private void updateQueue(int key) {
        queue.remove((Integer) key);
        queue.push(key);
    }

    public void display() {
        System.out.println(queue);
    }
}


