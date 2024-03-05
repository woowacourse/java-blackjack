package blackjack.model;

import java.util.Objects;

public class Card {

    private final CardPattern cardPattern;
    private final CardNumber cardNumber;

    public Card(CardPattern cardPattern, CardNumber cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }

    public int extractCardNumber() {
        return cardNumber.getNumber();
    }

    public boolean isAce() {
        return cardNumber == CardNumber.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardPattern == card.cardPattern && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardPattern, cardNumber);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
