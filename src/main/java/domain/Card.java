package domain;

public class Card {
    private final Symbol symbol;
    private final Number number;

    private Card(final Symbol symbol, final Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static Card createByStrategy(SymbolFinder randomSymbolFinder, NumberFinder randomNumberFinder) {
        Symbol symbol = randomSymbolFinder.find();
        Number number = randomNumberFinder.find();
        return new Card(symbol, number);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Number getNumber() {
        return number;
    }
}
