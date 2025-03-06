package blackjack.model;

import java.util.List;
import java.util.Objects;

public class Card {

    private final CardType cardType;
    private final CardNumber cardNumber;

    public Card(final CardType cardType, final CardNumber cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public List<Integer> getCardNumbers() {
        return cardNumber.getNumbers();
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card card)) {
            return false;
        }
        return cardType == card.cardType && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardType, cardNumber);
    }

}
