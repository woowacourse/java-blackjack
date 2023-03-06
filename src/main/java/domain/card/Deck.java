package domain.card;

import java.util.*;

public class Deck {
    private static final String ERROR_EMPTY_CARDS = "[ERROR] 뽑을 수 있는 카드가 존재하지 않습니다";

    private final Queue<Card> cards;

    private Deck(Queue<Card> cards) {
        this.cards = cards;
    }

    public static Deck create(ShuffleStrategy shuffleStrategy) {
        List<Card> cards = Cards.getInitCards();
        shuffleStrategy.shuffle(cards);

        return new Deck(new ArrayDeque<>(cards));
    }

    public Card pollAvailableCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ERROR_EMPTY_CARDS);
        }

        return cards.poll();
    }

    public List<Card> pollTwoCards() {
        return List.of(pollAvailableCard(), pollAvailableCard());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList((LinkedList<Card>) cards);
    }
}
