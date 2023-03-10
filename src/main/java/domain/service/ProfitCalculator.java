package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import domain.model.Profit;

public class ProfitCalculator {

    public Profit calculatePlayerProfit(final Player player, final Dealer dealer) {
        if (isBlackJackWin(player, dealer)) {
            return Profit.blackJackWin(player.getBatting());
        }
        if (isWin(player, dealer)) {
            return Profit.win(player.getBatting());
        }
        if (isDraw(player, dealer)) {
            return Profit.draw();
        }
        return Profit.defeat(player.getBatting());
    }

    private boolean isBlackJackWin(final Player player, final Dealer dealer) {
        return player.isBlackJack() && dealer.isNotBlackJack();
    }

    private boolean isWin(final Player player, final Dealer dealer) {
        return player.isStand() && winnable(player, dealer);
    }

    private boolean winnable(final Player player, final Dealer dealer) {
        return bothBlackJack(player, dealer)
            || player.getScore().moreThan(dealer.getScore())
            || dealer.isBust();
    }

    private boolean bothBlackJack(final Player player, final Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private boolean isDraw(final Player player, final Dealer dealer) {
        return player.getScore().equals(dealer.getScore());
    }
}
