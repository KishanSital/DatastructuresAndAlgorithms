package sr.unasat.money.exchange.simulator.datastructures.implementation;


import sr.unasat.money.exchange.simulator.datastructures.adt.QueueBfs;

public class QueueBfsImpl implements QueueBfs {
    private final int SIZE = 20;
    private int[] queArray;
    private int front;
    private int rear;

    // -------------------------------------------------------------
    public QueueBfsImpl()            // constructor
    {
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    // -------------------------------------------------------------
    @Override
    public void insert(int j) // put item at rear of queue
    {
        if (rear == SIZE - 1)
            rear = -1;
        queArray[++rear] = j;
    }

    // -------------------------------------------------------------
    @Override
    public int remove()       // take item from front of queue
    {
        int temp = queArray[front++];
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
    public int peek() {
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
