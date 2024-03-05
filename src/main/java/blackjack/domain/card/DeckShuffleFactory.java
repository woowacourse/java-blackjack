package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class DeckShuffleFactory implements DeckFactory {

    @Override
    public Stack<Card> generate() {
        Stack<Card> deck = new Stack<>();
        for (Number number : Number.values()) {
            for (Suit suit : Suit.values()) {
                deck.push(new Card(number, suit));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }
}
