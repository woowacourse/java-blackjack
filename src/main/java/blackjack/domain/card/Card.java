package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final CardType cardType;

    private static Map<String, Card> poll = new HashMap<>();

    private Card(CardNumber cardNumber, CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public static Card of(CardNumber cardNumber, CardType cardType) {
        return poll.computeIfAbsent(cardNumber.getNumber() + cardType.getType(),
                ignored -> new Card(cardNumber, cardType));
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardType getType() {
        return cardType;
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
        return cardNumber == card.cardNumber && cardType == card.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardType);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + cardNumber +
                ", cardType=" + cardType +
                '}';
    }
}
