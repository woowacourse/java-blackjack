package blackjack.domain.state;

import static blackjack.domain.state.FinishedState.ALREADY_FINISHED_EXCEPTION_MESSAGE;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.strategy.StayStrategy;

public class CanHit implements CardHand {

    private static final String NOT_FINISHED_STATE_EXCEPTION_MESSAGE = "아직 패가 확정되지 않았습니다.";

    private final CardBundle cardBundle;
    private final StayStrategy stayStrategy;

    public CanHit(final CardBundle cardBundle, final StayStrategy stayStrategy) {
        validateCanHit(cardBundle, stayStrategy);
        this.cardBundle = cardBundle;
        this.stayStrategy = stayStrategy;
    }

    private void validateCanHit(CardBundle cardBundle, StayStrategy stayStrategy) {
        if (stayStrategy.shouldStay(cardBundle)){
            throw new IllegalArgumentException(ALREADY_FINISHED_EXCEPTION_MESSAGE);
        }
    }

    public CardHand hit(final Card card) {
        CardBundle newCardBundle = cardBundle.addAndGenerate(card);

        if (newCardBundle.isBust()) {
            return new Bust(newCardBundle);
        }
        if (stayStrategy.shouldStay(newCardBundle)) {
            return new Stay(newCardBundle);
        }
        return new CanHit(newCardBundle, stayStrategy);
    }

    public CardHand stay() {
        return new Stay(cardBundle);
    }

    @Override
    public double getBettingYieldVersus(CardHand targetHand){
        throw new IllegalArgumentException(NOT_FINISHED_STATE_EXCEPTION_MESSAGE);
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
