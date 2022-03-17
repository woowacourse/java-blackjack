package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.CanHit;
import blackjack.domain.state.CardHand;
import blackjack.domain.state.Stay;
import blackjack.strategy.CanHitStrategy;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;

public abstract class Participant {

    protected CardHand cardHand;

    protected Participant(final CardBundle cardBundle, final CanHitStrategy stateStrategy) {
        if (cardBundle.isBlackjack()) {
            this.cardHand = new Blackjack(cardBundle);
        } else if (stateStrategy.checkFinished(cardBundle)) {
            this.cardHand = new Stay(cardBundle);
        } else {
            this.cardHand = new CanHit(cardBundle, stateStrategy);
        }
    }

    public boolean canDraw() {
        return !cardHand.isFinished();
    }

    public void drawAllCards(final HitOrStayChoiceStrategy hitOrStayStrategy,
                             final CardsViewStrategy viewStrategy,
                             final CardSupplier supplier) {
        while (canDraw()) {
            hitOrStay(hitOrStayStrategy, supplier);
            viewStrategy.print(this);
        }
    }

    public void hitOrStay(final HitOrStayChoiceStrategy hitOrStay,
                          final CardSupplier supplier) {
        if (hitOrStay.shouldHit()) {
            cardHand = cardHand.hit(supplier.getCard());
            return;
        }
        cardHand = cardHand.stay();
    }

    public Score getScore() {
        return getCardBundle().getScore();
    }

    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    protected boolean isBlackjackOrBust() {
        return cardHand.isBlackjack() || cardHand.isBust();
    }

    public abstract String getName();

    public abstract List<Card> getInitialOpenCards();

    public List<Card> getCards() {
        return getCardBundle().getCards();
    }

    private CardBundle getCardBundle() {
        return cardHand.getCardBundle();
    }
}
