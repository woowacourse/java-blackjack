package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(final CardNumber cardNumber, final CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public String getCardName() {
        return cardNumber.getInitial() + cardPattern.getName();
    }

    public int getCardNumber() {
        return cardNumber.getNumber();
    }

    public boolean isAceCard() {
        return cardNumber.equals(CardNumber.ACE);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card other = (Card) o;
        return (cardNumber == other.cardNumber) && (cardPattern == other.cardPattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardPattern, cardNumber);
    }

    @Override
    public String toString() {
        return "Card{" + getCardName() + '}';
    }

}
