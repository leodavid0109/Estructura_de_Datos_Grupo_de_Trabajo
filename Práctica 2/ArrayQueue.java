public class ArrayQueue {
    private Object[] data;
    private int first;
    private int rear;

    public ArrayQueue(int capacity) {
        data = new Object[capacity];
        first = -1;
        rear = -1;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear - first + 1;
    }

    public boolean isEmpty() {
        return first == -1 || first > rear;
    }

    public void enqueue(Object e) {
        if (isEmpty()) {
            first = 0;
            rear = 0;
        } else {
            rear++;
        }
        data[rear] = e;
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object e = data[first];
        if (first == rear) {
            first = -1;
            rear = -1;
        } else {
            first++;
        }
        return e;
    }

    public Object first() {
        if (isEmpty()) {
            return null;
        }
        return data[first];
    }
}
