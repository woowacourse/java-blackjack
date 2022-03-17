package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardHand;
import blackjack.domain.game.Score;
import blackjack.strategy.CardHandStateStrategy;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;

public abstract class Participant {

    protected CardHand cardHand;

    protected Participant(final CardBundle cardBundle, final CardHandStateStrategy stateStrategy) {
        this.cardHand = CardHand.of(cardBundle, stateStrategy);
    }

    public boolean canDraw() {
        return !cardHand.isFinished();
    }

    public void hitOrStay(final HitOrStayChoiceStrategy hitOrStay,
                          final CardSupplier supplier) {
        if (hitOrStay.shouldHit()) {
            receiveCard(supplier.getCard());
            return;
        }
        cardHand.stay();
    }

    abstract protected void receiveCard(final Card card);

    public Score getScore() {
        return cardHand.getScore();
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
        return cardHand.getCards();
    }
}
