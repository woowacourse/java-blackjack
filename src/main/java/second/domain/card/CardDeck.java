package second.domain.card;

import second.domain.ICardDeck;

import java.util.Collections;
import java.util.Stack;

public class CardDeck implements second.domain.ICardDeck {
    private final Stack<Card> cards = new Stack<>();

    public CardDeck() {
        this.cards.addAll(Card.makeAllCards());
        Collections.shuffle(this.cards);
    }

    @Override
    public Card pickCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 이상 카드를 뽑을 수 없습니다.");
        }

        return cards.pop();
    }
}
