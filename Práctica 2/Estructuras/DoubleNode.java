package Estructuras;

public class DoubleNode {
    private Object data;
    private DoubleNode next;
    private DoubleNode prev;

    public DoubleNode(){
        this.data = null;
        this.prev = null;
        this.next = null;
    }

    public DoubleNode(Object d){
        this.data = d;
        this.prev = null;
        this.next = null;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public void setPrev(DoubleNode prev) {
        this.prev = prev;
    }

    public Object getData() {
        return data;
    }

    public DoubleNode getNext() {
        return next;
    }

    public DoubleNode getPrev() {
        return prev;
    }
    
    public void remove() {
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
    }
}
