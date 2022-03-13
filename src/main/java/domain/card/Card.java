package domain.card;

public class Card {

    private final Symbol symbol;
    private final Denomination denomination;

    private Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
    }

    public static Card of(Symbol symbol, Denomination denomination){
        return new Card(symbol, denomination);
    }

    public int getScore(){
        return denomination.getScore();
    }

    public String getDenomination(){
        return denomination.getLetter();
    }

    public String getSymbol() {
        return symbol.getLetter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Card card = (Card) o;

        if (symbol != card.symbol) {
            return false;
        }
        return denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        return result;
    }
}
