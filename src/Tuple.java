public class Tuple<S1, S2> {
    private final int first;
    private final int second;

    public Tuple(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) obj;
        return first == tuple.first && second == tuple.second;
    }

    @Override
    public int hashCode() {
        return 31 * first + second; // Simple hash function
    }

    @Override
    public String toString() {
        return String.format("%d, %d", first, second);
    }
}
