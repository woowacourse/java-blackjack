package domain;

import common.ErrorMessage;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck(CardCreationStrategy strategy) {
        return new Deck(strategy.create());
    }

    // 메시지 : 카드를 만들어줘.

    public Card drawCard() {
        try {
            return cards.pop();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }
    }

    public List<Card> drawTwoCards() {
        try {
            return List.of(cards.pop(), cards.pop());
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }
    }
}
