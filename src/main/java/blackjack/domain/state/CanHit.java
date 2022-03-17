package blackjack.domain.state;

import static blackjack.domain.state.FinishedState.ALREADY_FINISHED_EXCEPTION_MESSAGE;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.strategy.CanHitStrategy;

public class CanHit implements CardHand {

    private final CardBundle cardBundle;
    private final CanHitStrategy stateStrategy;

    public CanHit(final CardBundle cardBundle,
                  final CanHitStrategy stateStrategy) {

        validateCanHit(cardBundle, stateStrategy);
        this.cardBundle = cardBundle;
        this.stateStrategy = stateStrategy;
    }

    private void validateCanHit(CardBundle cardBundle, CanHitStrategy stateStrategy) {
        if (stateStrategy.checkFinished(cardBundle)){
            throw new IllegalArgumentException(ALREADY_FINISHED_EXCEPTION_MESSAGE);
        }
    }

    public CardHand hit(final Card card) {
        CardBundle newCardBundle = cardBundle.addAndGenerate(card);

        if (newCardBundle.isBust()) {
            return new Bust(newCardBundle);
        }
        if (stateStrategy.checkFinished(newCardBundle)) {
            return new Stay(newCardBundle);
        }
        return new CanHit(newCardBundle, stateStrategy);
    }

    public CardHand stay() {
        return new Stay(cardBundle);
    }

    @Override
    public CardBundle getCardBundle() {
        return cardBundle;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public String toString() {
        return "CanHit{" + "cardBundle=" + cardBundle + '}';
    }
}
