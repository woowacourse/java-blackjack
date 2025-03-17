package participant;

import card.Card;
import card.CardHand;
import java.util.List;
import state.DealerHit;

public final class Dealer extends Participant {
    public Dealer(final CardHand initialHand) {
        super(DealerHit.initialState(initialHand));
    }

    @Override
    public List<Card> openInitialCard() {
        CardHand cardHand = state.cardHand();
        return List.of(cardHand.getFirstCard());
    }

//    @Override
//    public Bet getProfit() {
//        return new Bet(10000); // TODO 임시
//    }

    @Override
    public Name getName() {
        return new Name("딜러");
    }
}
