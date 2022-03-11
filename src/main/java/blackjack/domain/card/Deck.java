package blackjack.domain.card;

import java.util.*;

public class Deck {
    private static final int CARDS_SIZE = 52;

    private final Queue<Card> cards;

    private Deck(Queue<Card> cards) {
        validateDuplicate(cards);
        validateCardSize(cards);
        this.cards = cards;
    }

    public static Deck of(List<Card> cards) {
        Collections.shuffle(cards);
        return new Deck(new LinkedList<>(cards));
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 꺼낼 카드가 존재하지 않습니다.");
        }
        return cards.poll();
    }

    private void validateDuplicate(Queue<Card> cards) {
        if (cards.size() != Set.copyOf(cards).size()) {
            throw new IllegalArgumentException("카드는 중복될 수 없습니다.");
        }
    }

    private void validateCardSize(Queue<Card> cards) {
        if (cards.size() != CARDS_SIZE) {
            throw new IllegalArgumentException("카드는 52장으로 생성되어야 합니다.");
        }
    }
}