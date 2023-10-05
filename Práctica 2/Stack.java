public class Stack {
    private List data;

    public Stack() {
        data = new List();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(Object e) {
        data.addFirst(e);
    }

    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        return data.removeFirst();
    }

    public Object top() {
        if (isEmpty()) {
            return null;
        }
        return data.First().getData();
    }
}