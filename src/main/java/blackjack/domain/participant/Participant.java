package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.CanHit;
import blackjack.domain.state.CardHand;
import blackjack.domain.state.Stay;
import blackjack.strategy.StayStrategy;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;

public abstract class Participant {

    protected CardHand cardHand;

    protected Participant(final CardBundle cardBundle, final StayStrategy stateStrategy) {
        cardHand = getInitialCardHand(cardBundle, stateStrategy);
    }

    private CardHand getInitialCardHand(CardBundle cardBundle, StayStrategy strategy) {
        if (cardBundle.isBlackjack()) {
            return new Blackjack(cardBundle);
        }
        if (strategy.shouldStay(cardBundle)) {
            return new Stay(cardBundle);
        }
        return new CanHit(cardBundle, strategy);
    }

    public void drawAllCards(final HitOrStayChoiceStrategy hitOrStayStrategy,
                             final CardsViewStrategy viewStrategy,
                             final CardSupplier supplier) {
        while (canDraw()) {
            cardHand = hitOrStay(hitOrStayStrategy, supplier);
            viewStrategy.print(this);
        }
    }

    private CardHand hitOrStay(HitOrStayChoiceStrategy hitOrStay, CardSupplier supplier) {
        if (hitOrStay.shouldHit()) {
            return cardHand.hit(supplier.getCard());
        }
        return cardHand.stay();
    }

    public boolean canDraw() {
        return !cardHand.isFinished();
    }

    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public abstract List<Card> getInitialOpenCards();

    public CardHand getCardHand() {
        return cardHand;
    }

    public abstract String getName();

    public CardBundle getCardBundle() {
        return cardHand.getCardBundle();
    }
}
