package card;

import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(CardNumber cardNumber, CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public int getCardNumber() {
        return cardNumber.scores.get(0);
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
        return cardNumber == card.cardNumber && cardPattern == card.cardPattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardPattern);
    }

    public boolean isSameCardNumber(CardNumber checkNumber) {
        return cardNumber == checkNumber;
    }
}
