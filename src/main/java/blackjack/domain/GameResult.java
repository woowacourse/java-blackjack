package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class GameResult {

    final Map<Player, Profit> gameResult = new HashMap<>();

    public void decideWinner(final Dealer dealer, final Player player) {
        putResult(player, compete(dealer, player));
    }

    public void putResult(final Player player, final Profit result) {
        gameResult.put(player, result);
    }

    private Profit compete(final Dealer dealer, final Player player) {
        if (dealer.isWinner(player)) {
            return Profit.LOSE;
        }
        if (player.isBlackjack()) {
            return Profit.BLACKJACK_WIN;
        }
        if (player.isWinner(dealer)) {
            return Profit.WIN;
        }
        return Profit.DRAW;
    }

    public BettingMoney calculateProfit(Player player, BettingMoney money) {
        Profit result = gameResult.get(player);
        return result.calculateProfit(money);
    }
}
