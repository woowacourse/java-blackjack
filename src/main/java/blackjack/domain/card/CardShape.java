package blackjack.domain.card;

public enum CardShape {

    HEART("♥"),
    SPADE("♠"),
    CLOVER("♣"),
    DIAMOND("♦");

    private final String symbol;

    CardShape(String name) {
        this.symbol = name;
    }

    public String getSymbol() {
        return symbol;
    }
}
