package blackjack.domain.participants;

import blackjack.domain.game.GameResult;

public class Bet {
    private final long amount;

    public Bet() {
        this.amount = 0L;
    }

    public Bet(long amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private static void validatePositive(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    public Profit calculateProfit(GameResult gameResult) {
        Profit baseProfit = new Profit(amount);

        if (gameResult.isPlayerBlackjack()) {
            return baseProfit.applyBlackjackPayout();
        }
        if (gameResult.isPush()) {
            return Profit.zero();
        }
        if (gameResult.isPlayerWin()) {
            return baseProfit;
        }
        if (gameResult.isDealerWin()) {
            return baseProfit.negative();
        }
        throw new IllegalArgumentException("유효하지 않은 게임 결과로 수익을 계산할 수 없습니다.");
    }
}
