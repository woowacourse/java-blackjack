package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;

public abstract class FinishedState implements CardHand {

    public static final String ALREADY_FINISHED_EXCEPTION_MESSAGE = "이미 패가 확정된 참여자입니다.";
    protected final static double BLACKJACK_WIN_BETTING_YIELD = 1.5;
    protected final static double WIN_BETTING_YIELD = 1;
    protected final static double LOSE_BETTING_YIELD = -1;
    protected final static double DRAW_BETTING_YIELD = 0;

    protected CardBundle cardBundle;

    protected FinishedState(final CardBundle cardBundle) {
        this.cardBundle = cardBundle;
    }

    @Override
    public CardHand hit(Card card) {
        throw new IllegalArgumentException(ALREADY_FINISHED_EXCEPTION_MESSAGE);
    }

    @Override
    public CardHand stay() {
        throw new IllegalArgumentException(ALREADY_FINISHED_EXCEPTION_MESSAGE);
    }

    @Override
    public CardBundle getCardBundle() {
        return cardBundle;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
