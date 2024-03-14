package domain.cards;

import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private static final List<Card> cache = new ArrayList<>();

    static {
        for (CardShape cardShape : CardShape.values()) {
            addCardToCacheWithShape(cardShape);
        }
    }

    private static void addCardToCacheWithShape(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cache.add(new Card(cardNumber, cardShape));
        }
    }

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    private Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public static List<Card> makeCardDeck() {
        return new ArrayList<>(cache);
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public int score() {
        return cardNumber.getScore();
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardShape getCardShape() {
        return cardShape;
    }
}
