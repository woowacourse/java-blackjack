package domain.card;

import static domain.BlackjackGame.INITIAL_CARD_COUNT;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Deck {
    private static final String EMPTY_CARDS = "[ERROR] 뽑을 수 있는 카드가 존재하지 않습니다.";

    private final Queue<Card> cards;

    private Deck(Queue<Card> cards) {
        this.cards = cards;
    }

    public static Deck createWithAllCards() {
        List<Card> cards = Card.getAllTypesOfCard();
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }

    public Card draw() {
        validateIsEmpty();
        return cards.poll();
    }

    private void validateIsEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CARDS);
        }
    }

    public Hand drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            cards.add(draw());
        }
        return Hand.of(cards);
    }
}
