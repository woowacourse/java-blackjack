package blackjack.model.wallet;

import blackjack.model.result.Result;

public class PlayerBetWallet {

    private static final int MIN_BET_AMOUNT = 100;
    private static final int BET_AMOUNT_UNIT = 10;

    private final int betAmount;
    private int profitAmount;

    public PlayerBetWallet(int betAmount) {
        validateMinimumBetAmount(betAmount);
        validateBetAmountUnit(betAmount);
        this.betAmount = betAmount;
        this.profitAmount = 0;
    }

    public void registerProfitAmount(Result result) {
        validateAlreadySet();
        profitAmount = (int) (betAmount * result.getPayoutRate());
    }

    public int calculateNetProfit() {
        return profitAmount - betAmount;
    }

    private void validateMinimumBetAmount(int betAmount) {
        if (betAmount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 배팅은 100원부터 가능합니다.");
        }
    }

    private void validateBetAmountUnit(int betAmount) {
        if (betAmount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅은 10원 단위로만 가능합니다.");
        }
    }

    private void validateAlreadySet() {
        if (profitAmount != 0) {
            throw new IllegalArgumentException("[ERROR] 이미 수익금이 등록되어 있습니다.");
        }
    }

    public int getBetAmount() {
        return betAmount;
    }

    public int getProfitAmount() {
        return profitAmount;
    }
}
