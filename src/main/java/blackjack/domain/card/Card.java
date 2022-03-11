package blackjack.domain.card;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> CARDS = new HashMap<>();

    private final CardShape cardShape;
    private final CardNumber cardNumber;

    static {
        for (CardShape shape : CardShape.values()) {
            cacheCards(shape);
        }
    }

    private static void cacheCards(CardShape shape) {
        for (CardNumber number : CardNumber.values()) {
            CARDS.put(number.getName() + shape.getName(), new Card(shape, number));
        }
    }

    private Card(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public static Card getInstance(CardShape shape, CardNumber number) {
        return CARDS.get(number.getName() + shape.getName());
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public static List<Card> getCards() {
        return new LinkedList<>(CARDS.values());
    }

    public int getValue() {
        return cardNumber.getValue();
    }

    public String getName() {
        return cardNumber.getName();
    }

    public String getShape() {
        return cardShape.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return cardShape == card.cardShape && cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardNumber);
    }
}
