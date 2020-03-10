import card.Card;

import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<Card> cards;

    CardDeck(List<Card> cards) {
        Collections.shuffle(cards);
        this.cards = cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new RuntimeException("더 이상 뽑을 카드가 없습니다.");
        }
        return cards.remove(0);
    }
}
