package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    public static final String NO_MORE_CARDS = "더이상 뽑을 수 있는 카드가 없습니다.";
    private Stack<Card> cards = new Stack<>();

    public Deck() {
        for (Symbol symbol : Symbol.values()) {
            addCardWithDenomination(symbol);
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(NO_MORE_CARDS);
        }
        return cards.pop();
    }

    private void addCardWithDenomination(Symbol symbol) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(symbol, denomination));
        }
    }
}
