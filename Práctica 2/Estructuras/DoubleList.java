package Estructuras;

public class DoubleList {
    private DoubleNode head;
    private DoubleNode tail;
    private int size;

    public DoubleList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Boolean isEmpty(){
        if(size ==0){
            return true;
        }else{
            return false;
        }
    }

    public DoubleNode first(){
        return head;
    }

    public DoubleNode last(){
        return tail;
    }

    public void addFirst(Object e){
        DoubleNode n = new DoubleNode(e);
        if (isEmpty()){
            head = n;
            tail = n;
        }
        else{
            n.setNext(head);
            head.setPrev(n);
            head = n;
        }
        size++;

    }

    public void addLast(Object e){
        DoubleNode n = new DoubleNode(e);
        if (isEmpty()){
            head = n;
            tail = n;
        }
        else{
            tail.setNext(n);
            n.setPrev(tail);
            tail = n;
        }
        size++;

    }

    public Object removeFirst(){
        if (isEmpty()){
            return null;
        }
        else{
            DoubleNode temp = head;
            head = temp.getNext();
            temp.setNext(null);
            head.setPrev(null);
            size--;
            return temp.getData();
        }
    }

    public Object removeLast(){
        if (isEmpty()){
            return null;
        }
        else{
            DoubleNode temp = tail;
            tail = temp.getPrev();
            tail.setNext(null);
            temp.setPrev(null);
            size--;
            return temp;
        }
    }

    public Object remove(DoubleNode n){
        if (n == head){
            return removeFirst();
        }
        else if (n == tail) {
            return removeLast();
        }
        else {
            Object e = n.getData();
            DoubleNode temp_prev = n.getPrev();
            DoubleNode temp_next = n.getNext();
            temp_prev.setNext(temp_next);
            temp_next.setPrev(temp_prev);
            n.setNext(null);
            n.setPrev(null);
            size--;
            return e;
        }
    }

    public void addBefore(DoubleNode n, Object e){
        if (n == head){
            addFirst(e);
        }
        else {
            DoubleNode m = new DoubleNode(e);
            DoubleNode temp_prev = n.getPrev();
            temp_prev.setNext(m);
            m.setPrev(temp_prev);
            m.setNext(n);
            n.setPrev(m);
            size++;
        }
    }

    public void addAfter(DoubleNode n, Object e){
        if (n == tail){
            addLast(e);
        }
        else {
            DoubleNode m = new DoubleNode(e);
            DoubleNode temp_next = n.getNext();
            n.setNext(m);
            m.setPrev(n);
            m.setNext(temp_next);
            temp_next.setPrev(m);
            size++;
        }
    }


    public int size() {
        return size;
    }
}