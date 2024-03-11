package blackjack.model.wallet;

import blackjack.model.result.Result;

public class PlayerBetWallet extends BetWallet {

    private PlayerBetWallet(int betAmount) {
        super(betAmount);
    }

    public static PlayerBetWallet createByBetAmount(int betAmount) {
        return new PlayerBetWallet(betAmount);
    }

    public void calculatePayAmount(Result result) {
        profitAmount = (int) (betAmount * result.getPayoutRate());
    }
}
