package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import domain.model.Profit;
import domain.vo.Batting;

public class ProfitCalculator {

    public Profit calculate(final Batting batting, final Player player, final Dealer dealer) {
        if (isBlackJackWin(player, dealer)) {
            return Profit.blackJackWin(batting);
        }
        if (isWin(player, dealer)) {
            return Profit.win(batting);
        }
        if (isDraw(player, dealer)) {
            return Profit.draw();
        }
        return Profit.defeat(batting);
    }

    private boolean isBlackJackWin(final Player player, final Dealer dealer) {
        return player.isBlackJack() && dealer.isNotBlackJack();
    }

    private boolean isWin(final Player player, final Dealer dealer) {
        return player.isStand() && winable(player, dealer);
    }

    private boolean winable(final Player player, final Dealer dealer) {
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
