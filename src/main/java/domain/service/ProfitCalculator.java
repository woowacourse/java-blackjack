package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import domain.vo.Profit;
import domain.type.Result;

public class ProfitCalculator {

    public Profit calculatePlayerProfit(final Player player, final Dealer dealer) {
        final Result result = Result.findResult(player, dealer);
        if (result.equals(Result.BLACKJACK_VICTORY)) {
            return Profit.blackJackVictory(player.getBatting());
        }
        if (result.equals(Result.VICTORY)) {
            return Profit.victory(player.getBatting());
        }
        if (result.equals(Result.DRAW)) {
            return Profit.draw();
        }
        return Profit.defeat(player.getBatting());
    }
}
