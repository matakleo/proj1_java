package deque;
import java.util.Random;
import java.util.*;
public class ArrayDeque<T> implements Deque<T>,Iterable<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque ()  {
        items = (T[]) new Object[8];
        nextFirst = 4;
        size = 0;
        nextLast = 5;


    }
    private void moveFirstLeft(){
        nextFirst-=1;
        if (nextFirst==-1){
            nextFirst=items.length-1;
        }


    }
    private void moveFirstRight(){
        nextFirst+=1;
        if (nextFirst==items.length){
            nextFirst=0;
        }


    }
    @Override
    public void addFirst(T item) {
        size+=1;
        items[nextFirst]=item;
        moveFirstLeft();

    }

    private void moveLastRight() {
        nextLast += 1;
        if (nextLast == items.length ) {
            nextLast = 0;
        }
    }
    private void moveLastLeft() {
        nextLast -= 1;
        if (nextLast == -1) {
            nextLast = items.length-1;
        }
    }
    @Override
    public void addLast(T item) {
        size+=1;
        items[nextLast]=item;
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
        i=5;
        while(i != items.length){
            if(items[i]==null){
                i++;
                continue;
            }
            System.out.print(items[i]+" ");
            i++;

        }
        i=0;
        while(i!=5){
            if(items[i]==null){
                i++;
                continue;
            }
            System.out.print(items[i]+" ");
            i++;
        }
        System.out.println();


    }

    @Override
    public T removeFirst() {
        T item;
        if (nextFirst == items.length-1) {
            item = items[0];
            items[0] = null;
            moveFirstRight();
        }
        else {
            item=items[nextFirst+1];
            items[nextFirst+1]=null;
            moveFirstRight();
        }
        size-=1;
        return item;


    }

    @Override
    public T removeLast() {
        T item;
        if (nextLast==0) {
            item = items[items.length - 1];
            items[items.length - 1] = null;
            moveLastLeft();
        }
        else {
            item = items[nextLast-1];
            items[nextLast-1]=null;
            if (nextLast!=0) {
                moveLastLeft();
            }
        }
        size-=1;
        return item;
     }

    @Override
    public T get(int index) {
        T[] p = (T[]) new Object[items.length];
        int i,j;
        /** first border case */

        if (nextFirst==7){
            i=0;
            while (i< items.length){
                p[i]=items[i];
                i++;
            }
        }

//        /** if next first is in proper line */
//
//        else if (nextFirst<4) {
//            i = nextFirst + 1;
//            while (i < items.length ) {
//                p[i - nextFirst -1] = items[i];
//                i++;
//            }
//            i=0;
//            while (i < nextFirst + 1) {
//                p[items.length - nextFirst -1+i] = items[i];
//                i++;
//            }
//
//        }
        /** final catch */
        else {
            i = nextFirst + 1;
            while (i < items.length) {
                p[i - nextFirst -1] = items[i];
                i++;
            }
            i=0;
            while (i < nextFirst + 1) {
                p[items.length - nextFirst -1+i] = items[i];
                i++;
            }


            }
    return p[index];
        }

    public Iterator<T> iterator(){
        return new ALIterator();
    }


    @Override
    public boolean isEmpty() {
        return size>0;
    }


    private class ALIterator implements Iterator<T> {
        T curr;
        int index = 0;
        @Override
        public boolean hasNext() {
            return index<items.length && get(index)!=null;
        }

        @Override
        public T next() {
            if  (!hasNext()){
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
        if (!(o instanceof ArrayDeque)){
            return false;
        }
        else {
            ArrayDeque A = (ArrayDeque) o;
            if (size!=A.size()){
                return false;
            }
            for (int i=0; i<A.size();i++){
                if (A.get(i)!=get(i)){
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        ArrayDeque<Integer> A = new ArrayDeque<>();
        L.addFirst(2);
        A.addFirst(2);
        L.addLast(4);
        A.addLast(4);
        L.addLast(6);
        A.addLast(6);
        L.addLast(8);
        A.addLast(8);
//        L.addLast(10);
        A.addLast(10);
//        L.addLast(12);
//        L.addLast(14);
//        L.addLast(18);
//        A.removeFirst();

        for (Integer o: L){
            System.out.println(o);
        }

        System.out.print(L.equals(A));


    }
}
