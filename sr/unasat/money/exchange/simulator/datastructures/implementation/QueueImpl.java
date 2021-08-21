package sr.unasat.money.exchange.simulator.datastructures.implementation;


import sr.unasat.money.exchange.simulator.datastructures.adt.Queue;

public class QueueImpl<E> implements Queue<E> {
    private final int SIZE = 20;
    private E[] queArray;
    private int front;
    private int rear;

    // -------------------------------------------------------------
    public QueueImpl()            // constructor
    {
        queArray = (E[]) new Object[SIZE];
        front = 0;
        rear = -1;
    }

    // -------------------------------------------------------------
    @Override
    public void insert(E j) // put item at rear of queue
    {
        if (rear == SIZE - 1)
            rear = -1;
        queArray[++rear] = j;
    }

    // -------------------------------------------------------------
    @Override
    public E remove()       // take item from front of queue
    {
        E temp = queArray[front++];
        if (front == SIZE)
            front = 0;
        return temp;
    }

    // -------------------------------------------------------------
    @Override
    public boolean isEmpty()  // true if queue is empty
    {
        return (rear + 1 == front || (front + SIZE - 1 == rear));
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(1);
        }
        return queArray[front];
    }

    @Override
    public Boolean isFull() {
        return (size() == SIZE);
    }

    @Override
    public int size() {
        return queArray.length;
    }

}
