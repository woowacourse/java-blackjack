package blackjack.domain.card;

public enum Symbol {
    DIAMOND("♦"),
    HEART("♥"),
    SPADE("♠"),
    CLOVER("♣️");

    private final String name;

    Symbol(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
