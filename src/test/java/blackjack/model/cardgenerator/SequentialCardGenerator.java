package blackjack.model.cardgenerator;

import blackjack.model.card.Card;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SequentialCardGenerator implements CardGenerator {
    private final Iterator<Card> cards;

    public SequentialCardGenerator(final List<Card> cards) {
        this.cards = cards.iterator();
    }

    @Override
    public Card pick() {
        if (cards.hasNext()) {
            return cards.next();
        }
        throw new NoSuchElementException("이미 모든 cards가 반환되었습니다.");
    }
}
