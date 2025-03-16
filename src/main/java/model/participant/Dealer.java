package model.participant;

import java.util.List;
import model.card.Card;
import model.card.CardHand;
import model.state.DealerHit;

public final class Dealer extends Participant {
    public Dealer(final CardHand initialHand) {
        super(DealerHit.initialState(initialHand));
    }

    @Override
    public List<Card> openInitialCard() {
        CardHand cardHand = state.cardHand();
        return List.of(cardHand.getFirstCard());
    }

    @Override
    public Name getName() {
        return new Name("딜러");
    }
}
