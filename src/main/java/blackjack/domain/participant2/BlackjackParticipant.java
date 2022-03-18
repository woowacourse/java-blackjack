package blackjack.domain.participant2;

import blackjack.domain.card.CardBundle;
import blackjack.domain.hand.CardHand;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.CardsViewStrategy2;
import blackjack.strategy.HitOrStayChoiceStrategy;

public abstract class BlackjackParticipant implements Participant {

    protected CardHand cardHand;

    protected BlackjackParticipant(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public final void drawAll(final HitOrStayChoiceStrategy hitOrStay,
                              final CardsViewStrategy2 viewStrategy,
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
    public final CardBundle getCardBundle() {
        return cardHand.getCardBundle();
    }
}
