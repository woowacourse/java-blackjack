package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static CardDeck createGameDeck() {
        List<Card> pool = Card.createCardPool();
        Collections.shuffle(pool);
        return new CardDeck(pool);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드 덱이 비어 있습니다.");
        }
        return cards.remove(0);
    }

    public List<Card> drawDouble() {
        return new ArrayList<>(List.of(draw(), draw()));
    }
}
