package domain.participant;

import domain.card.Cards;
import domain.game.GamePoint;

import java.util.List;

public final class Dealer extends Participant {

    public static final GamePoint STANDARD_OF_NEED_MORE_CARD = GamePoint.of(16);

    protected Dealer(final Name name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(Name.of(DEALER_NAME));
    }

    public boolean needMoreCard() {
        return calculatePoint().isLowerThan(STANDARD_OF_NEED_MORE_CARD) && !this.isBusted();
    }

    public Cards getFirstCard() {
        return Cards.create(List.of(cards.getFirstCard()));
    }
}
