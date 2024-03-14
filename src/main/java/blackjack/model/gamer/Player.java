package blackjack.model.gamer;

import blackjack.model.gameRule.GameRule;
import blackjack.model.gameRule.Result;

public class Player extends Gamer {

    private final PlayerStatus playerStatus;

    private Player(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public Player(Name name, int betAmount) {
        this(new PlayerStatus(name, betAmount));
    }

    public void applyResult(Result result) {
        playerStatus.registerProfitAmount(result);
    }

    @Override
    public boolean canHit() {
        return totalScore() <= GameRule.PLAYER_HIT_MAX_SCORE;
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
