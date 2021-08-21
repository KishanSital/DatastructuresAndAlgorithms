package sr.unasat.money.exchange.simulator.datastructures.implementation;

import sr.unasat.money.exchange.simulator.datastructures.adt.Stack;

public class StackDfsImpl implements Stack {

    private final int SIZE = 20;
    private int[] st;
    private int top;

    // ------------------------------------------------------------
    public StackDfsImpl()           // constructor
    {
        st = new int[SIZE];    // make array
        top = -1;
    }

    // ------------------------------------------------------------
    @Override
    public void push(int j)   // put item on stack
    {
        st[++top] = j;
    }

    // ------------------------------------------------------------
    @Override
    public int pop()          // take item off stack
    {
        return st[top--];
    }

    // ------------------------------------------------------------
    @Override
    public int peek()         // peek at top of stack
    {
        return st[top];
    }

    // ------------------------------------------------------------
    @Override
    public boolean isEmpty()  // true if nothing on stack
    {
        return (top == -1);
    }
}
