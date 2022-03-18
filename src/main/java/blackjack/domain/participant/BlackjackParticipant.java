package blackjack.domain.participant;

import blackjack.domain.hand.CardHand;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;

public abstract class BlackjackParticipant implements Participant {

    protected CardHand cardHand;

    protected BlackjackParticipant(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public final void drawAll(final HitOrStayChoiceStrategy hitOrStay,
                              final CardsViewStrategy viewStrategy,
                              final CardSupplier supplier) {
        while (!cardHand.isFinished()) {
            cardHand = hitOrStay(hitOrStay, supplier);
            viewStrategy.print(this);
        }
    }

    private CardHand hitOrStay(HitOrStayChoiceStrategy hitOrStay, CardSupplier supplier) {
        if (!shouldStay() && hitOrStay.shouldHit()) {
            return cardHand.hit(supplier.getCard());
        }
        return cardHand.stay();
    }

    protected abstract boolean shouldStay();

    @Override
    public final CardHand getHand() {
        return cardHand;
    }
}
