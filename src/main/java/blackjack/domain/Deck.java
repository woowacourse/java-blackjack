package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private static final int CARDS_SIZE = 52;

    private final Queue<Card> cards;

    public Deck(List<Card> cards) {
        validate(cards);
        this.cards = new LinkedList<>(cards);
    }

    private void validate(List<Card> cards) {
        validateSize(cards);
        validateDuplicated(cards);
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != CARDS_SIZE) {
            throw new IllegalArgumentException("카드들의 갯수는 52장이어야 합니다.");
        }
    }

    private void validateDuplicated(List<Card> cards) {
        if (cards.size() != new HashSet<>(cards).size()) {
            throw new IllegalArgumentException("카드 덱은 중복될 수 없습니다.");
        }
    }

    public List<Card> draw(int count) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(this.cards.poll());
        }
        return drawnCards;
    }
}
