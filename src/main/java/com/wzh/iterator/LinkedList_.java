package com.wzh.iterator;

/**
 * @author wzh
 * @date 2020-06-05 20:58
 */
public class LinkedList_ implements Collection_ {

    Node head=null;
    Node tail=null;

    private int size=0;
    public void add(Object o) {
        Node n=new Node(o);
        n.next=null;
        if(head==null){
            head=n;
            tail=n;
        }
        tail.next=n;
        tail=n;
        size++;
    }

    private class Node{
        private Object o;
        Node next;

        public Node(Object o) {
            this.o = o;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator_ iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator_{

        private int currentIndex=0;

        private Node currentNode=null;

        @Override
        public boolean hasNext() {
            if(currentIndex >= size) return false;
            return true;
        }

        @Override
        public Object next() {
            if(currentNode==null) currentNode=head;
            else currentNode=currentNode.next;
            currentIndex++;
            return currentNode.o;
        }
    }
}
