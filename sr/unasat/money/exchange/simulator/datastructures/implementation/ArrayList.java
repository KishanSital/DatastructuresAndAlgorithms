package sr.unasat.money.exchange.simulator.datastructures.implementation;

import sr.unasat.money.exchange.simulator.datastructures.adt.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    private E[] data;
    private int count = 0;
    private int FIXED_SIZE = 10;

    public ArrayList() {
        data = (E[]) new Object[this.FIXED_SIZE];
    }

    public ArrayList(int customSize) {
        FIXED_SIZE = customSize;
        data = (E[]) new Object[FIXED_SIZE];

    }

    public E get(int index) {
        if (index < count) {
            return data[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(E obj) {
        if (data.length - count <= data.length / 2) {
            this.reSizeArray();
        }

        data[count++] = obj;
    }

    public E remove(int index) {
        if (index < count) {
            E obj = data[index];
            int temp = index;
            data[index] = null;

            while (temp < count) {
                data[temp] = data[temp + 1];
                data[temp + 1] = null;
                temp++;
            }

            count--;
            return obj;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void reSizeArray() {
        data = Arrays.copyOf(data, data.length * 2);
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

}