package deque;
import java.util.*;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

/** nodes which will be building blocks of the linked list dequeue */
    private class StuffNode{
        /** instance attributes which define our node */
        private T item;
        private StuffNode prev;
        private StuffNode next;

    /** constructor of the node */
    public StuffNode (StuffNode prev, T item, StuffNode next){
        this.prev = prev;
        this.item = item;
        this.next = next;
    }


    }


    /** instance attributes of our LinkedListDequeue */
    private StuffNode sentinel;
    private int size;
    /** instantiating an empty list */
    public LinkedListDeque(){

        size = 0;
        /** size is zero, no elements, and we want both prev and next
         * to be pointing back to the sentinel pointer which is a stuff node;
         */
        /** here you make our sentinel  actually point to a stuff node, but the trick is
         * never to put anything into this stuffnode, it will just be there to keep everything in order
         */
        sentinel = new StuffNode(null,null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;

    }

    /**now we make a new constructor when we immediately want to put an item in the next stuff node*/
    public LinkedListDeque(T x){
        size += 1;
        sentinel = new StuffNode(null,null,null);
        sentinel.next=new StuffNode(sentinel, x, sentinel);
        sentinel.prev=sentinel.next;


    }


    @Override
    public void addFirst(T item) {
        size += 1;
        /** we add a new element whose prev will be pointing towards sentinel
         * and its next will be pointing towards whatever the sentinel.next was pointing
         * to before.
         */
        sentinel.next=new StuffNode(sentinel, item, sentinel.next);
        /** this is to make the prev part of the node that used to be after sentinel point
         * towards the newly added node!
         */
        sentinel.next.next.prev=sentinel.next;

    }

    @Override
    public void addLast(T item) {
        size +=1;
        /** this adds another node at the end of the list */
        sentinel.prev=new StuffNode(sentinel.prev, item, sentinel);
        /** this makes the node that used to be in the end now point towards the new end*/
        sentinel.prev.prev.next=sentinel.prev;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        /**pointer to keep track of first item after sentinel*/
        StuffNode P = sentinel.next;
        /** loop over all the nodes and print items until make full circle */
        while (sentinel.next!=sentinel){
            System.out.print(sentinel.next.item+" ");
            sentinel.next=sentinel.next.next;
        }
        System.out.println("");
        /** finally return the pointer of the sentinel to the first node after it */
        sentinel.next=P;

    }

    @Override
    /**Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst() {
        /** to hold the return value */
        StuffNode returner;
        /** if nothing in the list, return null */
        if (size<1){
            return null;
        }
        returner = sentinel.next;
        /** go over the first item, make the pointers cross it from both sides */
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
        size-=1;
        return returner.item;


    }

    @Override
    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        /** to hold the return value */
        StuffNode returner;
        if (size<1){
            return null;
        }
        returner = sentinel.prev;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next=sentinel;
        size-=1;
        return returner.item;
    }

    @Override
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        StuffNode pointer=sentinel.next;
        int counter = 0;
        while(counter<index){
            pointer=pointer.next;
            counter++;
        }
        return pointer.item;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public T getRecursive(int index){
        if (index == 0){
            return sentinel.next.item;
        }
        sentinel=sentinel.next;
        return getRecursive(index-1);
    }

    @Override
    public Iterator<T> iterator(){
        return new LLIterator();
    }
    private class LLIterator implements Iterator<T> {
        StuffNode pointer=sentinel.next;

        @Override
        public boolean hasNext() {
            return pointer.prev!=sentinel.prev;
        }

        @Override
        public T next() {
            if  (!hasNext()){
                throw new NoSuchElementException();
            }
            /** split next into three parts!*/
            /** part 1, get the current item */
            T curr = pointer.item;
            /** part 2, advance the iterator */
            pointer=pointer.next;
            /** part 3 return the item */
            return curr;

        }
    }
    /** Returns whether or not the parameter o is equal to the Deque.
     *  o is considered equal if it is a Deque and if it contains the same contents
     *  (as goverened by the generic T’s equals method) in the same order.*/

    public boolean equals(Object o){

        /** if not Linked list return false */
        if (!(o instanceof Deque)){
            return false;
        }
        else {
            Deque O = ((Deque) o);
            /** if not the same size, return false */
            if (size!=O.size()){
                return false;
            }
            for (int i=0; i<O.size();i++){
                if (!(O.get(i).equals(get(i)))){
                    return false;
                }
            }
            return true;
        }


    }




}
