package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {
    private static final Map<String, Card> CARD_POOL = new HashMap<>();

    private final CardRank cardRank;
    private final CardShape cardShape;

    static {
        for (CardRank cardRank : CardRank.values()) {
            for (CardShape cardShape : CardShape.values()) {
                CARD_POOL.put(toKey(cardRank, cardShape), new Card(cardRank, cardShape));
            }
        }
    }

    private Card(CardRank cardRank, CardShape cardShape) {
        this.cardRank = cardRank;
        this.cardShape = cardShape;
    }

    public static Card of(CardRank cardRank, CardShape cardShape) {
        return CARD_POOL.get(toKey(cardRank, cardShape));
    }

    private static String toKey(CardRank cardRank, CardShape cardShape) {
        return cardRank.getRank() + cardShape.getShape();
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public String getName() {
        return cardRank.getRank() + cardShape.getShape();
    }

    public int getScore() {
        return cardRank.getScore();
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
        return cardRank == card.cardRank && cardShape == card.cardShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardRank, cardShape);
    }
}
