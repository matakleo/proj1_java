package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> implements Deque<T>,Iterable<T>  {
    Comparator<T> comparator;
    int size;
    /** new constructor */
    public MaxArrayDeque(Comparator<T> c) {
        this.comparator=c;
        size=0;

    }
    /** returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null */
    public T max(){
        T item;
        if (size<1){
            return null;
        }
        return null;
    }
    /** returns the maximum element in the deque as governed by the parameter Comparator c.
     *  If the MaxArrayDeque is empty, simply return null. */
    public T max(Comparator<T> c){
        T item;
        if (size<1){
            return null;
        }
        return null;
    }
    @Override
    public void addFirst(T item) {

    }

    @Override
    public void addLast(T item) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
