package blackjack.model.gamer;

import blackjack.model.result.Result;

public class Player extends Gamer {

    private final PlayerStatus playerStatus;

    private Player(Name name, int betAmount) {
        this.playerStatus = new PlayerStatus(name, betAmount);
    }

    public static Player of(Name name, int betAmount) {
        return new Player(name, betAmount);
    }

    public void applyResult(Result result) {
        playerStatus.registerProfitAmount(result);
    }

    public String name() {
        return playerStatus.name();
    }

    public int betAmount() {
        return playerStatus.betAmount();
    }

    public int profitAmount() {
        return playerStatus.profitAmount();
    }

    public int netProfit() {
        return playerStatus.calculateNetProfit();
    }
}
