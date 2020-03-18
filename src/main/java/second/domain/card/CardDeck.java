package second.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck implements CardProviable {
    private static final String NO_MORE_CARD_EXIST_MESSAGE = "더 이상 카드를 뽑을 수 없습니다.";

    private final Stack<Card> cards = new Stack<>();

    public CardDeck() {
        this.cards.addAll(Card.makeAllCards());
        Collections.shuffle(this.cards);
    }

    @Override
    public Card pickCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(NO_MORE_CARD_EXIST_MESSAGE);
        }

        return cards.pop();
    }
}
