public class Queue {
    private List data;

    public Queue() {
        data = new List();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void enqueue(Object e) {
        data.addLast(e);
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        return data.removeFirst();
    }

    public Object first() {
        if (isEmpty()) {
            return null;
        }
        return data.First().getData();
    }
}