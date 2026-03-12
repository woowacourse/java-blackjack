package domain.participant;

import domain.MatchResult;

public class Bet {

    private static final double WIN_WITH_BLACKJACK_PAYOUT_RATIO = 1.5;
    private static final int WIN_PAYOUT_RATIO = 1;
    private static final int DRAW_PAYOUT_RATIO = 0;
    private static final int LOSE_PAYOUT_RATIO = -1;

    private static final int MAX_BET_AMOUNT = 100_000_000;
    private static final int BET_UNIT = 1000;

    private final int amount;

    public static Bet of(int amount) {
        return new Bet(amount);
    }

    private Bet(int amount) {
        validatePositiveAmount(amount);
        validateBetUnit(amount);
        validateMaxBetAmount(amount);
        this.amount = amount;
    }

    public int calculateProfit(MatchResult result, boolean isBlackJack) {
        if (result == MatchResult.WIN) {
            if (isBlackJack) return (int) (amount * WIN_WITH_BLACKJACK_PAYOUT_RATIO);

            return amount * WIN_PAYOUT_RATIO;
        }

        if (result == MatchResult.DRAW) return amount * DRAW_PAYOUT_RATIO;
        return amount * LOSE_PAYOUT_RATIO;
    }

    private void validatePositiveAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(String.format("잘못된 배팅 금액: %d (배팅 금액은 0보다 커야 합니다.)", amount));
        }
    }

    private void validateBetUnit(int amount) {
        if (amount % BET_UNIT != 0) {
            throw new IllegalArgumentException(String.format("잘못된 배팅 금액: %d (배팅 금액은 %d원 단위로 입력해야 합니다.)", amount, BET_UNIT));
        }
    }

    private void validateMaxBetAmount(int amount) {
        if (amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(String.format("잘못된 배팅 금액: %d (배팅 금액은 %d을 초과할 수 없습니다.)", amount, MAX_BET_AMOUNT));
        }
    }
}
