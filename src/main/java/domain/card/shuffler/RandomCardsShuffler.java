package domain.card.shuffler;

import domain.card.Card;
import java.util.Collections;
import java.util.Stack;

public class RandomCardsShuffler implements CardsShuffler {
    @Override
    public Stack<Card> shuffleCards(final Stack<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
