package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private Symbol symbol;
    private CardNumber cardNumber;

    public Card(Symbol symbol, CardNumber cardNumber) {
        this.symbol = symbol;
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return cardNumber.getName() + symbol.getName();
    }

    public int getScore() {
        return cardNumber.getScore();
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
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
        return symbol == card.symbol && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, cardNumber);
    }

    @Override
    public String toString() {
        return cardNumber.getName() + symbol.getName();
    }

}
