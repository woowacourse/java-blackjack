package blackjack.model.cards;

import blackjack.model.Card;
import blackjack.model.Score;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class ChangeableCards implements Cards {

    private final HardHandCards cards;

    ChangeableCards(HardHandCards cards) {
        this.cards = cards;
    }

    public final Score hardHandScore() {
        return cards.score();
    }

    public final ChangeableCards toMixHand() {
        return new MixHandCards(cards);
    }

    public final ChangeableCards toSoftHand() { return new SoftHandCards(cards); }

    @Override
    public final Stream<Card> stream() {
        return cards.stream();
    }

    @Override
    public final void take(Card card) {
        cards.take(card);
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
