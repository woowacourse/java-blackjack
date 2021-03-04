package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private final Stack<Card> deck = new Stack<>();

    public Deck(List<Card> cards) {
        this.deck.addAll(cards);
        Collections.shuffle(deck);
    }

    public Card pop() {
        if (deck.empty()) {
            throw new RuntimeException("덱이 비어있습니다.");
        }
        return deck.pop();
    }

}
