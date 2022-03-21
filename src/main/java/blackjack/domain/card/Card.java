package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> cacheCard = new HashMap<>(52);

    private final CardNumber cardNumber;
    private final Suit Suit;

    private Card(CardNumber cardNumber, Suit Suit) {
        this.cardNumber = cardNumber;
        this.Suit = Suit;
    }

    public static Card of(CardNumber cardNumber, Suit Suit) {
        String key = cardNumber.name() + Suit.name();
        if (!cacheCard.containsKey(key)) {
            cacheCard.put(key, new Card(cardNumber, Suit));
        }
        return cacheCard.get(key);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public Suit getSuit() {
        return Suit;
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
        return cardNumber == card.cardNumber && Suit == card.Suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, Suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + cardNumber +
                ", Suit=" + Suit +
                '}';
    }
}
