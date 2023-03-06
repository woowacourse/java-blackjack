package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>(Card.values());
        shuffleCards();
    }

    CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    private void shuffleCards() {
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("뽑을 수 있는 카드가 없습니다.");
        }
        return cards.remove(0);
    }
}
