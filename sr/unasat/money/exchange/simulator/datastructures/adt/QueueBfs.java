package sr.unasat.money.exchange.simulator.datastructures.adt;

public interface QueueBfs {
    // -------------------------------------------------------------
    void insert(int j) // put item at rear of queue
    ;

    // -------------------------------------------------------------
    int remove()       // take item from front of queue
    ;

    // -------------------------------------------------------------
    boolean isEmpty()  // true if queue is empty
    ;

    int peek();

    Boolean isFull();

    int size();
}
