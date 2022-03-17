package blackjack.domain.card;

public class Card {

    public static final int ADDITIONAL_ACE_VALUE = 10;

    private final Symbol symbol;
    private final Denomination denomination;

    public Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    public int getValue() {
        return denomination.getValue();
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public String getDenominationName() {
        return denomination.getName();
    }

    public String getSymbolName() {
        return symbol.getName();
    }

    @Override
    public String toString() {
        return "Card{" +
                "symbol=" + symbol +
                ", denomination=" + denomination +
                '}';
    }
}
