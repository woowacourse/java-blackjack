package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomGenerator implements DeckGenerator {
    @Override
    public Stack<Card> generate() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Number number : Number.values()) {
                deck.add(new Card(number, suit));
            }
        }
        Collections.shuffle(deck);
        return toStack(deck);
    }

    private Stack<Card> toStack(List<Card> list) {
        Stack<Card> stack = new Stack<>();
        stack.addAll(list);
        return stack;
    }
}
