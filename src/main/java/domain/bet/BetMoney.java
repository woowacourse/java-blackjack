package domain.bet;

import domain.result.WinLossResult;

public class BetMoney {

    private static final int DRAW_PROFIT = 0;
    private static final double BLACKJACK_BONUS = 1.5;
    private static final int WIN_BONUS = 2;
    private static final int BET_AMOUNT_UNIT = 1000;
    private static final String INVALID_UNIT_BET_AMOUNT_INPUT = "베팅 금액은 1000원 단위여야 합니다.";

    private final int amount;

    public BetMoney(final int amount) {
        validateBetAmount(amount);
        this.amount = amount;
    }

    public BetMoney computeProfit(final WinLossResult winLossResult) {
        if(winLossResult == WinLossResult.WIN) {
            return applyWinBonus();
        }
        if(winLossResult == WinLossResult.BLACKJACK_WIN) {
            return applyBlackJackBonus();
        }
        if(winLossResult == WinLossResult.LOSS) {
            return applyLossPenalty();
        }
        return new BetMoney(DRAW_PROFIT);
    }

    private static void validateBetAmount(final double parsedBetAmount) {
        if(parsedBetAmount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_UNIT_BET_AMOUNT_INPUT);
        }
    }

    private BetMoney applyBlackJackBonus() {
        return new BetMoney((int) (amount * BLACKJACK_BONUS - amount));
    }

    private BetMoney applyWinBonus() {
        return new BetMoney(amount * WIN_BONUS - amount);
    }

    private BetMoney applyLossPenalty() {
        return new BetMoney(-amount);
    }

    public double getAmount() {
        return amount;
    }

}
