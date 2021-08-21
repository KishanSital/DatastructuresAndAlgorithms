package sr.unasat.money.exchange.simulator.datastructures.implementation;

import sr.unasat.money.exchange.simulator.datastructures.adt.PriorityQueue;
import sr.unasat.money.exchange.simulator.entities.Edge;

public class PriorityQueueImpl implements PriorityQueue {

    // array in sorted order, from max at 0 to min at size-1
    private final int SIZE = 20;
    private Edge[] queArray;
    private int size;

    // -------------------------------------------------------------
    public PriorityQueueImpl()            // constructor
    {
        queArray = new Edge[SIZE];
        size = 0;
    }

    // -------------------------------------------------------------
    @Override
    public void insert(Edge item)  // insert item in sorted order
    {
        int j;

        for (j = 0; j < size; j++)           // find place to insert
            if (item.distance >= queArray[j].distance)
                break;

        for (int k = size - 1; k >= j; k--)    // move items up
            queArray[k + 1] = queArray[k];

        queArray[j] = item;             // insert item
        size++;
    }

    // -------------------------------------------------------------
    @Override
    public Edge removeMin()            // remove minimum item
    {
        return queArray[--size];
    }

    // -------------------------------------------------------------
    @Override
    public void removeN(int n)         // remove item at n
    {
        for (int j = n; j < size - 1; j++)     // move items down
            queArray[j] = queArray[j + 1];
        size--;
    }

    // -------------------------------------------------------------
    @Override
    public Edge peekMin()          // peek at minimum item
    {
        return queArray[size - 1];
    }

    // -------------------------------------------------------------
    @Override
    public int size()              // return number of items
    {
        return size;
    }

    // -------------------------------------------------------------
    @Override
    public boolean isEmpty()      // true if queue is empty
    {
        return (size == 0);
    }

    // -------------------------------------------------------------
    @Override
    public Edge peekN(int n)      // peek at item n
    {
        return queArray[n];
    }

    // -------------------------------------------------------------
    @Override
    public int find(int findDex)  // find item with specified
    {                          // destVert value
        for (int j = 0; j < size; j++)
            if (queArray[j].destVert == findDex)
                return j;
        return -1;
    }
// -------------------------------------------------------------
}
