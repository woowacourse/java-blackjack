package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards = new Stack<>();

    public Deck(List<Card> cards) {
        if (cards.isEmpty() || cards == null) {
            throw new IllegalArgumentException("카드 덱을 생성할 수 없습니다.");
        }
        Collections.shuffle(cards);
        this.cards.addAll(cards);
    }

    public Card pick() {
        if (cards.empty()) {
            throw new EmptyStackException();
        }
        return cards.pop();
    }
}
