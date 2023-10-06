package Estructuras;

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
        int temp;
        temp = data.length - first + rear;
        temp = temp % data.length + 1;
        return temp;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(Object e) {
        if (size() < data.length){ //Verificamos disponibilidad
            //Calculamos la posición con mod (%)
            rear = (rear + 1) % data.length;
            data[rear] = e; //Insertamos el dato en la cola
        }
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null; //Si la cola está vacía, retornamos nulo
        }
        else{
            Object temp = data[first]; //Almacenamos el dato a retornar
            data[first] = null; //Eliminamos el dato de la cola
            first = (first + 1) % data.length; //Calculamos la nueva posición de first
            return temp; //Retornamos el dato
        }
    }

    public Object first() {
        if (isEmpty()) {
            return null;
        }
        return data[first];
    }
}
