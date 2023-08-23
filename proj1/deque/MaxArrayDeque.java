package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T>  {
    private Comparator<T> comparator;
    private int size;
    /** new constructor */
    public MaxArrayDeque(Comparator<T> c) {
        this.comparator=c;
        size=0;

    }
    /** returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null */
    public T max(){

        if (size<1){
            return null;
        }
        T item = get(0);
        for (T o : this) {
           if (comparator.compare(o, item) >= 0){
               item = o;
           }
        }
        return item;
    }
    /** returns the maximum element in the deque as governed by the parameter Comparator c.
     *  If the MaxArrayDeque is empty, simply return null. */
    T max(Comparator<T> c){
        if (size<1){
            return null;
        }
        T item = get(0);
        for (T o : this){
            if (c.compare(o, item) >= 0){
                item = o;
            }
        }
        return item;
    }

}
