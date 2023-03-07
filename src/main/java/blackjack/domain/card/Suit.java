package blackjack.domain.card;

public enum Suit {
    HEART("❤️"),
    SPADE("♠️"),
    CLOVER("♣️"),
    DIAMOND("♦️");

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
