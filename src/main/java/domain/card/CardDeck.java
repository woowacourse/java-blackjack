package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> cards = new Stack<>();

    CardDeck(List<Card> cards) {
        Collections.shuffle(cards);
        this.cards.addAll(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new RuntimeException("더 이상 뽑을 카드가 없습니다.");
        }
        return cards.pop();
    }
}
