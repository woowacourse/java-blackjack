package domain.state;

import domain.card.Card;
import domain.card.Hand;

public final class DealerHit extends Running {
    DealerHit(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        return super.stay();
    }

    @Override
    public State draw(Card card) {
        final Hand newHand = getHand().add(card);

        if(newHand.isDealerHit())
            return new DealerHit(newHand);
        if(newHand.isBust())
            return new Bust(newHand);
        return new Stay(newHand);
    }
}
