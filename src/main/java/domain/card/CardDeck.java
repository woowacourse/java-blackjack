package domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private Stack<Card> cards;

    CardDeck(Stack<Card> cards) {
        Collections.shuffle(cards);
        this.cards = cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new RuntimeException("더 이상 뽑을 카드가 없습니다.");
        }
        return cards.pop();
    }
}
