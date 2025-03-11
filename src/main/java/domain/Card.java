package domain;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final CardRank cardRank;

    public Card(Symbol symbol, CardRank cardRank) {
        this.symbol = symbol;
        this.cardRank = cardRank;
    }

    public boolean isAce() {
        return cardRank == CardRank.ACE;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public int getPoint() {
        return cardRank.getPoint();
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
        return symbol == card.symbol && cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, cardRank);
    }
}
