package domain.participant;

import domain.card.Card;
import java.util.List;

public final class Dealer extends Participant {

    private static final Name DEFAULT_DEALER = new Name("딜러");

    public Dealer(final List<Card> cards) {
        super(DEFAULT_DEALER, cards);
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isHittableForDealer();
    }
}
