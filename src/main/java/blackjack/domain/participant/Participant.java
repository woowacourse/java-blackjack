package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;
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

    private CardHand getInitialCardHand(CardBundle cardBundle, StayStrategy stayStrategy) {
        if (cardBundle.isBlackjack()) {
            return new Blackjack(cardBundle);
        }
        if (stayStrategy.checkFinished(cardBundle)) {
            return new Stay(cardBundle);
        }
        return new CanHit(cardBundle, stayStrategy);
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

    public Score getScore() {
        return getCardBundle().getScore();
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

    public abstract String getName();

    public abstract List<Card> getInitialOpenCards();

    public List<Card> getCards() {
        return getCardBundle().getCards();
    }

    protected boolean isBlackjackOrBust() {
        return cardHand.isBlackjack() || cardHand.isBust();
    }

    private CardBundle getCardBundle() {
        return cardHand.getCardBundle();
    }
}
