package blackjack.model.gamer;

import blackjack.model.gameRule.Result;
import blackjack.model.wallet.PlayerBetWallet;

public class PlayerStatus {

    private final Name name;
    private final PlayerBetWallet playerBetWallet;

    private PlayerStatus(Name name, PlayerBetWallet playerBetWallet) {
        this.name = name;
        this.playerBetWallet = playerBetWallet;
    }

    public PlayerStatus(Name name, int betAmount) {
        this(name, new PlayerBetWallet(betAmount));
    }

    public void registerProfitAmount(Result result) {
        playerBetWallet.registerProfitAmount(result);
    }

    public int calculateNetProfit() {
        return playerBetWallet.calculateNetProfit();
    }

    public String name() {
        return name.getName();
    }

    public int betAmount() {
        return playerBetWallet.getBetAmount();
    }

    public int profitAmount() {
        return playerBetWallet.getProfitAmount();
    }
}
