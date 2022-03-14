package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardHand;
import blackjack.domain.game.Score;
import blackjack.strategy.CardHandStateStrategy;
import java.util.List;

public abstract class Participant {

    protected CardHand cardHand;

    protected Participant(final CardBundle cardBundle, final CardHandStateStrategy stateStrategy) {
        this.cardHand = CardHand.of(cardBundle, stateStrategy);
    }

    public abstract void receiveCard(Card card);

    public boolean canDraw() {
        return !cardHand.isFinished();
    }

    public Score getCurrentScore() {
        return cardHand.getScore();
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
        return cardHand.getCards();
    }
}
