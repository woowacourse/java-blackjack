package blakjack.domain.participant;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public final class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final String CANNOT_HIT_MESSAGE = "딜러는 카드를 더 받을 수 없습니다";
    private static final int THRESHOLD_HIT_SCORE = 16;
    private static final int DEFAULT_MONEY = 1000;

    public Dealer() {
        super(new PrivateArea(DEALER_NAME), new Chip(DEFAULT_MONEY));
    }

    @Override
    public void hit(final Card card) {
        if (state.getTotalScore() > THRESHOLD_HIT_SCORE) {
            state = state.stay();
            throw new IllegalStateException(CANNOT_HIT_MESSAGE);
        }
        state = state.draw(card);
        state = state.stay();
    }
}
