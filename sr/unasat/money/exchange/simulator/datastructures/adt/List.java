package sr.unasat.money.exchange.simulator.datastructures.adt;

public interface List<E> {

    E get(int index);

    void add(E obj);

    E remove(int index);

    int size();

    boolean isEmpty();
}
