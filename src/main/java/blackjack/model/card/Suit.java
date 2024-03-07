package blackjack.model.card;

public enum Suit {
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    CLUB("클로버");

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public static int getSize() {
        return values().length;
    }

    public static Suit findByIndex(final int index) {
        return values()[index];
    }

    public String getName() {
        return name;
    }
}
