package balckjack.domain;

abstract public class Card {

    protected final String symbol;

    public Card(String symbol) {
        validateSymbol(symbol);
        this.symbol = symbol;
    }

    protected void validateSymbol(String symbol) {}

    abstract protected int getValue();

}
