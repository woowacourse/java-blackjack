package blackjack.domain.user;

import blackjack.domain.result.Result;

public class BetAmount {

    private static final int MINIMUM_AMOUNT = 500;
    private static final int MAXIMUM_AMOUNT = 1_000_000;
    private static final double BLACKJACK_ALLOCATION = 1.5;

    private final int betAmount;
    private double receivingAmount;

    public BetAmount(int betAmount) {
        validateAmountRange(betAmount);
        this.betAmount = betAmount;
        this.receivingAmount = betAmount;
    }

    private void validateAmountRange(int amount) {
        if (amount < MINIMUM_AMOUNT || amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("입력 가능한 배팅 금액은 최소 %d원 이상 %d원 이하입니다.", MINIMUM_AMOUNT, MAXIMUM_AMOUNT)
            );
        }
    }

    public double getReceivingAmount() {
        return receivingAmount;
    }

    public void updateReceivingAmount(Result result) {
        if (result.equals(Result.LOSE)) {
            receivingAmount = (-betAmount);
        }
    }

    public void calculateBlackjack() {
        receivingAmount = betAmount * BLACKJACK_ALLOCATION;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
