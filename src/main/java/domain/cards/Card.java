package domain.cards;

import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (CardShape cardShape : CardShape.values()) {
            addCardToCacheWithShape(cardShape);
        }
    }

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    private Card(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    private static void addCardToCacheWithShape(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            CACHE.add(new Card(cardNumber, cardShape));
        }
    }

    public static Card valueOf(CardNumber cardNumber, CardShape cardShape) {
        return CACHE.stream()
                .filter(card -> card.getCardNumber() == cardNumber)
                .filter(card -> card.getCardShape() == cardShape)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[INTERNAL ERROR] 카드 정보에 해당하는 카드를 찾을 수 없었습니다"));
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
