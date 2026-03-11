package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class BlackjackHand extends FinishedHand {

    public BlackjackHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    public double getEarningRate() {
        return 1.5;
    }

    @Override
    protected Hand nextState() {
        return new BlackjackHand(cards);
    }
}
