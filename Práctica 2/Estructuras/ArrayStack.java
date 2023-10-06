package Estructuras;

public class ArrayStack {
    private Object[] data;
    private int top;

    public ArrayStack(int capacity) {
        this.data = new Object[capacity];
        this.top = -1;
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(Object e) {
        if (top < data.length - 1){
            top ++;
            data[top] = e;
        }
        else{
            System.out.println("Estructuras.Stack overflow");
        }
    }

    public Object pop() {
        if (!isEmpty()) {
            Object temp = data[top];
            data[top] = null;
            top --;
            return temp;
        }
        else{
            return null;
        }
    }

    public Object top() {
        if (!isEmpty()) {
            return data[top];
        }
        return null;
    }
}
