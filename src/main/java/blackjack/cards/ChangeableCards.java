package blackjack.cards;

import blackjack.Card;
import blackjack.Score;
import java.util.stream.Stream;

public abstract class ChangeableCards implements Cards {

    private final HardHandCards cards;

    public ChangeableCards(Card... cards) {
        this.cards = new HardHandCards(cards);
    }

    public ChangeableCards(HardHandCards cards) {
        this.cards = cards;
    }

    public Score hardHandScore() {
        return cards.score();
    }

    public ChangeableCards toMixHand() {
        return new MixHandCards(cards);
    }

    @Override
    public Stream<Card> stream() {
        return cards.stream();
    }
}
