package blackjack.domain.card;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Deck {

    private static final int CARDS_SIZE = 52;

    private final Queue<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>(Card.createDeck());
        validateDuplicate(this.cards);
        validateCardSize(this.cards);
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
