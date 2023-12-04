package tools;

import java.util.Objects;

public class Pair<T> {
    private T one;
    private T two;

    public Pair(T one, T two) {
        this.one = one;
        this.two = two;
    }

    public T getOne() {
        return one;
    }

    public T getTwo() {
        return two;
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair other = (Pair) obj;
        return Objects.equals(one, other.one) && Objects.equals(two, other.two);
    }

    @Override
    public String toString() {
        return "("+one.toString()+";"+two.toString()+")";
    }
}
