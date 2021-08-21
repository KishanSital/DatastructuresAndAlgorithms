package sr.unasat.money.exchange.simulator.datastructures.adt;

public interface Stack {
    // ------------------------------------------------------------
    void push(int j)   // put item on stack
    ;

    // ------------------------------------------------------------
    int pop()          // take item off stack
    ;

    // ------------------------------------------------------------
    int peek()         // peek at top of stack
    ;

    // ------------------------------------------------------------
    boolean isEmpty()  // true if nothing on stack
    ;
}
