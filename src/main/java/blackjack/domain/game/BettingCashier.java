package blackjack.domain.game;

import blackjack.domain.participant.Player;

public class BettingCashier {

    private final Betting betting;
    private final Result result;

    public BettingCashier(Betting betting, Result result) {
        this.betting = betting;
        this.result = result;
    }

    public int findProfitOf(Player player) {
        int money = betting.get(player);
        if (result.isPlayerWon(player)) {
            return money;
        }
        if (result.isPlayerLose(player)) {
            return -money;
        }
        return 0;
    }
}
