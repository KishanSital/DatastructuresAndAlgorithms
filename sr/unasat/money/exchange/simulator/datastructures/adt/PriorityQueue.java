package sr.unasat.money.exchange.simulator.datastructures.adt;

import sr.unasat.money.exchange.simulator.entities.Edge;

public interface PriorityQueue {
    void insert(Edge item);

    Edge removeMin();

    void removeN(int n);

    Edge peekMin();

    int size();

    boolean isEmpty();

    Edge peekN(int n);

    int find(int findDex);
}
