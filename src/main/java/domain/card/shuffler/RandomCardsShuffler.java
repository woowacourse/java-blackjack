package domain.card.shuffler;

import domain.card.Card;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class RandomCardsShuffler implements CardsShuffler {
    @Override
    public Deque<Card> shuffleCards(final Deque<Card> cards) {
        List<Card> newCards = new ArrayList<>(cards);
        Collections.shuffle(newCards);
        return new ArrayDeque<>(newCards);
    }
}
