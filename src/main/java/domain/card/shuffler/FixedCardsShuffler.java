package domain.card.shuffler;

import domain.card.Card;
import java.util.Deque;
import java.util.Stack;

public final class FixedCardsShuffler implements CardsShuffler {
    @Override
    public Deque<Card> shuffleCards(final Deque<Card> cards) {
        return cards;
    }
}
