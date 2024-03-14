package domain.game;

public class Index {
    private static final int FIRST_PLAYER_INDEX = 1;
    private final int size;
    private final int current;

    public Index(int size) {
        this.size = size;
        this.current = FIRST_PLAYER_INDEX;
    }

    private Index(int size, int current) {
        this.size = size;
        this.current = current + 1;
    }

    public Index next() {
        return new Index(size, current);
    }

    public boolean isEnd() {
        return current != size;
    }

    public int getCurrent() {
        return current;
    }

}
