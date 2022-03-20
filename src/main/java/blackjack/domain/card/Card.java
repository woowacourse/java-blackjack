package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> cacheCard = new HashMap<>(52);

    private final CardNumber cardNumber;
    private final Type type;

    private Card(CardNumber cardNumber, Type type) {
        this.cardNumber = cardNumber;
        this.type = type;
    }

    public static Card of(CardNumber cardNumber, Type type) {
        String key = cardNumber.name() + type.name();
        if (!cacheCard.containsKey(key)) {
            cacheCard.put(key, new Card(cardNumber, type));
        }
        return cacheCard.get(key);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public Type getType() {
        return type;
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
        return cardNumber == card.cardNumber && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, type);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + cardNumber +
                ", type=" + type +
                '}';
    }
}
