package domain.card.shuffler;

import domain.card.Card;
import java.util.Collections;
import java.util.Stack;

public class RandomDeckShuffler implements DeckShuffler {
    @Override
    public Stack<Card> shuffleDeck(final Stack<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
}
