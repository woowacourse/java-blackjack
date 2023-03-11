package blackjack.domain.result;

import blackjack.domain.participants.BettingMoney;

public enum JudgeResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    PUSH(0),
    LOSE(-1);

    private final double profitRate;

    JudgeResult(final double profitRate) {
        this.profitRate = profitRate;
    }

    // TODO score의 역할?
    public static JudgeResult matchWithoutBlackJackConsider(final int selfScore, final int counterScore) {
        if (selfScore > counterScore) {
            return WIN;
        }
        if (selfScore < counterScore) {
            return LOSE;
        }
        return PUSH;
    }

    public JudgeResult counter() {
        if (this == BLACKJACK_WIN) {
            return LOSE;
        }
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public int profit(final BettingMoney bettingMoney) {
        return (int) (profitRate * (bettingMoney.getAmount()));
    }
}
