package domain;

import java.util.Objects;

public class Card {

    private final CardSymbol cardSymbol;
    private final CardRank cardRank;

    public Card(CardSymbol cardSymbol, CardRank cardRank) {
        this.cardSymbol = cardSymbol;
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

    public CardSymbol getCardSymbol() {
        return cardSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardSymbol == card.cardSymbol && cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSymbol, cardRank);
    }
}
