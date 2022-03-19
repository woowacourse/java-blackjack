package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public abstract class Finished extends Started {

    protected final static double BLACKJACK_WIN_BETTING_YIELD = 1.5;
    protected final static double WIN_BETTING_YIELD = 1;
    protected final static double LOSE_BETTING_YIELD = -1;
    protected final static double DRAW_BETTING_YIELD = 0;
    private final static String ALREADY_FINISHED_EXCEPTION_MESSAGE = "이미 확정된 패입니다.";

    protected Finished(CardBundle cardBundle) {
        super(cardBundle);
    }

    @Override
    public final CardHand hit(Card card) {
        throw new UnsupportedOperationException(ALREADY_FINISHED_EXCEPTION_MESSAGE);
    }

    @Override
    public final CardHand stay() {
        throw new UnsupportedOperationException(ALREADY_FINISHED_EXCEPTION_MESSAGE);
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public double profit(CardHand dealerHand, int money) {
        return calculateDuelResult(dealerHand) * money;
    }

    protected abstract double calculateDuelResult(CardHand dealerHand);
}
