package blackjack.model.game;

import blackjack.model.card.Card;
import java.util.List;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
    }


    public int getCardCount() {
        return cards.size();
    }

    public Card drawCard() {
        return cards.pop();
    }
}
