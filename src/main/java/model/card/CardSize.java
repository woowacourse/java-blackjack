package model.card;

public enum CardSize {
    TWO(2),
    ;

    private final int size;

    CardSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
