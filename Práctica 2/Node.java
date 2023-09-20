public class Node {
    private Object data;
    private Node next;

    public Node(){
        this.data = null;
        this.next = null;
    }

    public Node(Object e){
        this.data = e;
        this.next = null;
    }

    public Object getData() {
        return data;
    }

    public Node getNext(){
        return next;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
