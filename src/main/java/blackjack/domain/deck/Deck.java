package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards = new Stack<>();

    public Deck(List<Card> cards) {

        Collections.shuffle(cards);
        this.cards.addAll(cards);
    }

    public Card pick() {

        if (cards.empty()) {
            throw new NullPointerException("카드를 모두 사용하셨습니다.");
        }
        return cards.pop();
    }


}
