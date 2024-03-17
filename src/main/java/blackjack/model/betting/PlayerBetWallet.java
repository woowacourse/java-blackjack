package blackjack.model.betting;

import blackjack.model.gameRule.Result;

public class PlayerBetWallet {

    private final BetAmount betAmount;
    private ProfitAmount profitAmount;

    public PlayerBetWallet(int betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public void registerProfitAmount(Result result) {
        validateAlreadySet();
        int profitAmount = (int) (betAmount.getBetAmount() * result.getPayoutRate());
        this.profitAmount = new ProfitAmount(profitAmount);
    }

    public int calculateNetProfit() {
        return profitAmount.getProfitAmount() - betAmount.getBetAmount();
    }

    private void validateAlreadySet() {
        if (profitAmount != null) {
            throw new IllegalArgumentException("[ERROR] 이미 수익금이 등록되어 있습니다.");
        }
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }

    public int getProfitAmount() {
        return profitAmount.getProfitAmount();
    }
}
