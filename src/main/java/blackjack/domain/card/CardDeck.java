package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public static CardDeck createGameDeck() {
        List<Card> pool = Card.createCardPool();
        Collections.shuffle(pool);
        return new CardDeck(pool);
    }

    public Card draw() {
        validateNotEmpty();
        return cards.poll();
    }

    private void validateNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드 덱이 비어 있습니다.");
        }
    }

    public List<Card> drawDouble() {
        return new ArrayList<>(List.of(draw(), draw()));
    }
}
