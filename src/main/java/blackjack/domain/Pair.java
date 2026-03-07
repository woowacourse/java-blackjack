package blackjack.domain;

public class Pair<T, U> {

    private final T left;
    private final U right;

    public Pair(final T left, final U right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public U getRight() {
        return right;
    }
}
