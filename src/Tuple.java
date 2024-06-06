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

    public String toString() {
        return String.format("%d, %d", second,first);
    }
}
