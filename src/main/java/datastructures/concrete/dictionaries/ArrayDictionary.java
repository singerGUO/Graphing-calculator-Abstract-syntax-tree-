package datastructures.concrete.dictionaries;

import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

/**
 * @see datastructures.interfaces.IDictionary
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field.
    // We will be inspecting it in our private tests.
    private Pair<K, V>[] pairs;
    private int maxSize;
    private int size;

    // You may add extra fields or helper methods though!

    public ArrayDictionary() {
        maxSize = 10;
        size = 0;
        pairs = makeArrayOfPairs(maxSize);
    }

    /**
     * This method will return a new, empty array of the given maxSize
     * that can contain Pair<K, V> objects.
     * <p>
     * <p>
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            for (int i = 0; i < size; i++) {
                if (pairs[i].key == null) {
                    return pairs[i].value;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (pairs[i].key != null && pairs[i].key.equals(key)) {
                    return pairs[i].value;
                }
            }
        }
        throw new NoSuchKeyException();
    }

    @Override
    public void put(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        if (containsKey(key)) {
            if (key == null) {
                for (int i = 0; i < size; i++) {
                    if (pairs[i].key == null) {
                        pairs[i].value = value;
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    if (pairs[i].key != null && pairs[i].key.equals(key)) {
                        pairs[i].value = value;
                    }
                }
            }
        } else {
            if (size == maxSize) {
                extendsCapacity();
            }
            pairs[size++] = pair;
        }
    }

    //array can't be resized
    private void extendsCapacity() {
        // Pair<K,V>[] newArr = new Pair[maxSize * 2];
        Pair<K, V>[] newArr = makeArrayOfPairs(maxSize * 2);
        for (int i = 0; i < maxSize; i++) {
            newArr[i] = pairs[i];
        }
        maxSize *= 2;
        pairs = newArr;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            for (int i = 0; i < size; i++) {
                if (pairs[i].key == null) {
                    V val = pairs[i].value;
                    pairs[i] = pairs[size - 1];
                    size--;
                    return val;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (pairs[i].key != null && pairs[i].key.equals(key)) {
                    V val = pairs[i].value;
                    pairs[i] = pairs[size - 1];
                    size--;
                    return val;
                }
            }
        }
        throw new NoSuchKeyException();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            for (int i = 0; i < size; i++) {
                if (pairs[i].key == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (pairs[i].key != null && pairs[i].key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    // map.put(apple, map.getOrDefault(apple, 0) + 1);

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}