package domain.deck;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("더 이상 카드가 존재하지 않습니다. 지나친 도박은 삼가해주세요.");
        }
        return cards.poll();
    }

    public List<Card> getInitialGameCards() {
        return List.of(drawCard(), drawCard());
    }
}
