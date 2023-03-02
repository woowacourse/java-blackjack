package domain;

import java.util.List;
import java.util.Stack;

public class FixedCardsShuffler implements CardsShuffler {
    @Override
    public Stack<Card> shuffleCards(final Stack<Card> cards) {
        return cards;
    }
}
