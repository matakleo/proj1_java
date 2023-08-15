package deque;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;
import java.util.*;
public class ArrayDeque<T> implements Deque<T>,Iterable<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        size = 0;
        nextLast = 5;


    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize() {
        int i = 0;
        T[] a = (T[]) new Object[size * 2];
        for (T o : this) {
            a[size / 2 + i] = o;
            i++;
        }
        items = a;
        nextFirst = size / 2 - 1;
        nextLast = nextFirst + size + 1;
    }

    private void desize() {
        int i = 0;
        T[] a = (T[]) new Object[items.length / 2];
        for (T o : this) {
            a[size / 2 + i] = o;
            i++;
        }
        items = a;
        nextFirst = size / 2 - 1;
        nextLast = nextFirst + size + 1;
    }

    private void moveFirstLeft() {
        nextFirst -= 1;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }


    }

    private void moveFirstRight() {
        nextFirst += 1;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }


    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize();
        }
        size += 1;
        items[nextFirst] = item;
        moveFirstLeft();

    }

    private void moveLastRight() {
        nextLast += 1;
        if (nextLast == items.length) {
            nextLast = 0;
        }
    }

    private void moveLastLeft() {
        nextLast -= 1;
        if (nextLast == -1) {
            nextLast = items.length - 1;
        }
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize();
        }
        size += 1;
        items[nextLast] = item;
        moveLastRight();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i;
        /** if nextfirst is actually last place on array */
        i = 5;
        while (i != items.length) {
            if (items[i] == null) {
                i++;
                continue;
            }
            System.out.print(items[i] + " ");
            i++;

        }
        i = 0;
        while (i != 5) {
            if (items[i] == null) {
                i++;
                continue;
            }
            System.out.print(items[i] + " ");
            i++;
        }
        System.out.println();


    }

    @Override
    public T removeFirst() {
        if (size < 1) {
            return null;
        }
        T item;
        if (nextFirst == items.length - 1) {
            item = items[0];
            items[0] = null;
            moveFirstRight();
        } else {
            item = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            moveFirstRight();
        }
        size -= 1;
        if (size < 0.25 * items.length){
            desize();
        }
        return item;


    }

    @Override
    public T removeLast() {
        if (size < 1) {
            return null;
        }
        T item;
        if (nextLast == 0) {
            item = items[items.length - 1];
            items[items.length - 1] = null;
            moveLastLeft();
        } else {
            item = items[nextLast - 1];
            items[nextLast - 1] = null;
            if (nextLast != 0) {
                moveLastLeft();
            }
        }
        size -= 1;
        if (size < 0.25 * items.length){
            desize();
        }
        return item;
    }

    @Override
    public T get(int index) {


        int i, j;
        /** first border case */

        if (nextFirst + 1 == items.length) {
            return items[index];
        }
        if (nextFirst > size / 2 - 1) {
            if (index + nextFirst >= items.length - 1) {
                int pero = items.length - index;
                return items[nextFirst - pero + 1];
            }

        }
        if (nextFirst + index + 1 >= items.length) {
            return items[1 + nextFirst + index - items.length];
        }
        return items[nextFirst + index + 1];


        /** final catch */

    }

    public Iterator<T> iterator() {
        return new ALIterator();
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private class ALIterator implements Iterator<T> {
        T curr;
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < items.length && get(index)!=null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            /** split next into three parts!*/
            /** part 1, get the current item */
            curr = get(index);
            /** part 2, advance the iterator */
            index++;
            /** part 3 return the item */
            return curr;

        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        } else {
            Deque A = (Deque) o;
            if (size != A.size()) {
                return false;
            }
            for (int i = 0; i < A.size(); i++) {
                if (!(A.get(i).equals(get(i)))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        LinkedListDeque<Integer> B = new LinkedListDeque<>();
//        ArrayDeque<Integer> A = new ArrayDeque<>();
        for (int i = 0; i < 2500; i++) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                L.addLast(i);
                B.addLast(i);
            } else {
                L.addFirst(i);
                B.addFirst(i);
            }
            if (!L.equals(B)) {
                System.out.println("not equal");
                L.printDeque();
                B.printDeque();

            }
        }
        for (int i = 0; i < 2500; i++) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                L.removeLast();
                B.removeLast();
            } else {
                L.removeFirst();
                B.removeFirst();
            }
            if (!L.equals(B)) {
                System.out.println("not equal");
                L.printDeque();
                B.printDeque();

            }




        }
//        System.out.println(B);
//        System.out.print("[");
//        for (Integer o : L) {
//            System.out.print(o + ", ");
//        }
//
//        System.out.print("]");
    }
}
