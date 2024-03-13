package blackjack.model.gamer;

import blackjack.model.result.Result;
import blackjack.model.wallet.PlayerBetWallet;

public class PlayerStatus {

    private final Name name;
    private final PlayerBetWallet playerBetWallet;

    public PlayerStatus(Name name, int betAmount) {
        this.name = name;
        this.playerBetWallet = PlayerBetWallet.from(betAmount);
    }

    public String name() {
        return name.getName();
    }

    public void registerProfitAmount(Result result) {
        playerBetWallet.registerProfitAmount(result);
    }

    public int calculateNetProfit() {
        return playerBetWallet.calculateNetProfit();
    }

    public int betAmount() {
        return playerBetWallet.getBetAmount();
    }

    public int profitAmount() {
        return playerBetWallet.getProfitAmount();
    }
}
