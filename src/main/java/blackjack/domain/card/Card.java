package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final CardKind cardKind;
    private final CardNumber cardNumber;

    public Card(CardKind cardKind, CardNumber cardNumber) {
        this.cardKind = cardKind;
        this.cardNumber = cardNumber;
    }

    public boolean hasValue(CardNumber targetCardNumber) {
        return cardNumber == targetCardNumber;
    }

    public int getScore() {
        return cardNumber.getScore();
    }

    public CardKind getKind() {
        return cardKind;
    }

    public CardNumber getValue() {
        return cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardKind == card.cardKind && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardKind, cardNumber);
    }
}
