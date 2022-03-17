package blackjack.domain.card;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<Type, Map<CardNumber, Card>> cacheCard;

    static {
        cacheCard = new EnumMap<>(Type.class);
        for (Type type : Type.values()) {
            cacheCard.put(type, new EnumMap<>(CardNumber.class));
        }
    }

    private final CardNumber cardNumber;
    private final Type type;

    private Card(CardNumber cardNumber, Type type) {
        this.cardNumber = cardNumber;
        this.type = type;
    }

    public static Card of(CardNumber cardNumber, Type type) {
        if (!cacheCard.containsKey(type)) {
            cacheCard.put(type, new EnumMap<>(CardNumber.class));
        }
        if (!cacheCard.get(type).containsKey(cardNumber)) {
            cacheCard.get(type).put(cardNumber, new Card(cardNumber, type));
        }
        return cacheCard.get(type).get(cardNumber);
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
