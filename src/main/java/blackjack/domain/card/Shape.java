package blackjack.domain.card;

public enum Shape {

    HEART, DIAMOND, CLOVER, SPADE;

    public boolean isSameName(String name) {
        return this.name().equals(name);
    }
}
