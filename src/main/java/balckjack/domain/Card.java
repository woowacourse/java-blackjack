package balckjack.domain;

abstract public class Card {

    protected final Pattern pattern;
    protected final String symbol;

    public Card(Pattern pattern, String symbol) {
        validateSymbol(symbol);
        this.pattern = pattern;
        this.symbol = symbol;
    }

    protected void validateSymbol(String symbol) {}

    abstract protected int getValue();

}
