package domain.card;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Rank rank;

    public Card(Symbol symbol, Rank number) {
        this.symbol = symbol;
        this.rank = number;
    }

    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }

    public Rank getRank() {
        return rank;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && getRank() == card.getRank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, getRank());
    }
}
