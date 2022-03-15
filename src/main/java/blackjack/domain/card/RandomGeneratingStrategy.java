package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomGeneratingStrategy implements CardGeneratingStrategy {

    @Override
    public Stack<Card> generate() {
        final List<Card> allCards = Card.getAllCards();
        Collections.shuffle(allCards);

        final Stack<Card> deck = new Stack<>();
        deck.addAll(allCards);

        return deck;
    }
}
