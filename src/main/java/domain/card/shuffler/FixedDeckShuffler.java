package domain.card.shuffler;

import domain.card.Card;
import java.util.Stack;

public class FixedDeckShuffler implements DeckShuffler {
    @Override
    public Stack<Card> shuffleDeck(final Stack<Card> deck) {
        return deck;
    }
}
