package blackjack.domain.deck;

public enum Shape {

    HEART, DIAMOND, CLOVER, SPADE;

    public boolean isSameName(String name) {
        return this.name().equals(name);
    }
}
