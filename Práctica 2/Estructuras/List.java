package Estructuras;

public class List {
    private Node head;
    private Node tail;
    private int size;

    public List(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void setSize(int s){
        size = s;
    }

    public Node First(){
        return head;
    }

    public Node Last(){
        return tail;
    }

    public void addFirst(Object e){
        Node n = new Node(e);
        if (isEmpty()){
            head = n;
            tail = n;
        }
        else{
            n.setNext(head);
            head = n;
        }
        size++;
    }

    public void addLast(Object e){
        Node n = new Node(e);
        if (isEmpty()){
            head = n;
            tail = n;
        }
        else{
            tail.setNext(n);
            tail = n;
        }
        size++;
    }

    public Object removeFirst(){
        if (!isEmpty()){
            Node temp = head;
            head = temp.getNext();
            temp.setNext(null);
            size--;
            return temp.getData();
        }
        else{
            return null;
        }
    }

    public Object removeLast(){
        if (size == 1){
            return removeFirst();
        }
        else{
            Node temp = tail;
            Node anterior = head;
            while (anterior.getNext() != tail){
                anterior = anterior.getNext();
            }
            anterior.setNext(null);
            tail = anterior;
            size--;
            return temp.getData();
        }
    }
}
