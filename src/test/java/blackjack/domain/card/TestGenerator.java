package blackjack.domain.card;

import java.util.Stack;

public class TestGenerator implements DeckGenerator {

    private Stack<Card> deck = new Stack<>();

    public TestGenerator(Card... cards) {
        for (Card card : cards) {
            deck.push(card);
        }
    }

    @Override
    public Stack<Card> generate() {
        return this.deck;
    }

}
