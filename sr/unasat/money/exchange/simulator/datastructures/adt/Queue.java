package sr.unasat.money.exchange.simulator.datastructures.adt;

public interface Queue<E> {
    void insert(E j);

    E remove();

    boolean isEmpty();

    E peek();

    Boolean isFull();

    int size();
}
