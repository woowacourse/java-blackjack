package blackjack.model;

public enum Suit {
    HEART,
    SPADE,
    DIAMOND,
    CLUB;

    public static int getSize() {
        return values().length;
    }

    public static Suit findByIndex(final int index) {
        return values()[index];
    }
}
