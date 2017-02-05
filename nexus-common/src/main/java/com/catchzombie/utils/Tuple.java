package com.catchzombie.utils;

/**
 * Created by shubham on 4/2/17.
 */
public class Tuple<U,V> {
    public final U first;
    public final V second;

    public Tuple(U first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple Tuple = (Tuple) o;

        return !(first != null ? !first.equals(Tuple.first) : Tuple.first != null)
                && !(second != null ? !second.equals(Tuple.second) : Tuple.second != null);

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "<" + first + "," + second + ">";
    }


    public int compareTo(Tuple<U, V> o) {
        int value = ((Comparable<U>)first).compareTo(o.first);
        if (value != 0)
            return value;
        return ((Comparable<V>)second).compareTo(o.second);
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }}
