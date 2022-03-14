package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private static final int INIT_PROVIDING_CARD_SIZE = 2;

    private final Queue<Card> cards;

    private CardDeck(final Queue<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck generate() {
        List<Card> cards = Card.getAllCards();
        Collections.shuffle(cards);
        return new CardDeck(new LinkedList<>(cards));
    }

    public List<Card> provideInitCards() {
        validateEnoughDeckSize();
        return Arrays.asList(cards.poll(), cards.poll());
    }

    private void validateEnoughDeckSize() {
        if (cards.size() < INIT_PROVIDING_CARD_SIZE) {
            throw new IllegalStateException("남은 카드가 2장 미만입니다.");
        }
    }

    public Card provideCard() {
        validateEmptyDeck();
        return cards.poll();
    }

    private void validateEmptyDeck() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("남은 카드가 없습니다.");
        }
    }
}
