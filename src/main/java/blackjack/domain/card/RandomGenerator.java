package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomGenerator implements DeckGenerator {
    @Override
    public Stack<Card> generate() {
        List<Card> deck = Card.getDeck();
        Collections.shuffle(deck);
        return toStack(deck);
    }

    private Stack<Card> toStack(List<Card> list) {
        Stack<Card> stack = new Stack<>();
        stack.addAll(list);
        return stack;
    }
}
