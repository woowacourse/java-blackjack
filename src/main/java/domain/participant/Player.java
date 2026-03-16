package domain.participant;

import static exception.ErrorMessage.BET_AMOUNT_NOT_DIVIDED_BY_TEN;
import static exception.ErrorMessage.BET_AMOUNT_OUT_OF_RANGE;

public class Player extends Participant {

    public static final int MINIMUM_BET_AMOUNT = 0;
    public static final int MAXIMUM_BET_AMOUNT = 1_000_000;

    private final int betAmount;

    public Player(final Name name, final int betAmount) {
        super(name);

        // TODO: 베팅 금액 검증 테스트 추가
        validateBetAmountRange(betAmount);
        validateBetAmountDividedByTen(betAmount);

        this.betAmount = betAmount;
    }

    @Override
    public boolean isDrawable() {
        return !isBust() && !isBlackjack();
    }


    public int getBetAmount() {
        return betAmount;
    }


    private void validateBetAmountRange(final int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT || betAmount > MAXIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException(BET_AMOUNT_OUT_OF_RANGE.getMessage());
        }
    }

    private void validateBetAmountDividedByTen(final int betAmount) {
        if (betAmount % 10 != 0) {
            throw new IllegalArgumentException(BET_AMOUNT_NOT_DIVIDED_BY_TEN.getMessage());
        }
    }
}
