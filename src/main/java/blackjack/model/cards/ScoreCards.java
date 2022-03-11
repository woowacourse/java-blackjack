package blackjack.model.cards;

import blackjack.model.card.Card;
import blackjack.model.blackjack.Score;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class ScoreCards implements Cards {

    private final Cards cards;

    public ScoreCards(Cards cards) {
        this.cards = cards;
    }

    @Override
    public void take(Card card) {
        cards.take(card);
    }

    @Override
    public Stream<Card> stream() {
        return cards.stream();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public Cards openCard(int count) {
        return cards.openCard(count);
    }

    public boolean lessThan(Score score) {
        return score().lessThan(score);
    }

    public abstract Score score();
}
