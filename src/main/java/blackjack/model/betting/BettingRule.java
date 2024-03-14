package blackjack.model.betting;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;

public class BettingRule {
    private final Dealer dealer;

    public BettingRule(final Dealer dealer) {
        this.dealer = dealer;
    }

    public double calculateProfitRate(final Player player, final ResultCommand resultCommand) {
        if (resultCommand.equals(ResultCommand.WIN)) {
            return calculateProfitRateWhenPlayerWin(player);
        }
        return 0;
    }

    private double calculateProfitRateWhenPlayerWin(final Player player) {
        if (player.isBlackJack()) {
            return 1.5;
        }

        if (dealer.isBust()) {
            return 0;
        }

        return 1;
    }
}
