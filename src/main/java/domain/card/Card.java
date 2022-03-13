package domain.card;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Denomination denomination;

    public Card(Symbol symbol, Denomination denomination) {
        this.symbol = symbol;
        this.denomination = denomination;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, denomination);
    }
}
