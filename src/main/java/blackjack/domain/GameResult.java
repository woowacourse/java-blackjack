package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class GameResult {

    Map<Player, String> gameResult = new HashMap<>();

    public void determine(final Dealer dealer, final Player player) {
        putResult(player, compete(dealer, player));
    }

    public void putResult(final Player player, final String result) {
        gameResult.put(player, result);
    }

    private String compete(final Dealer dealer, final Player player) {
        if (player.isBlackjack()) {
            return "블랙잭";
        }
        if (dealer.isWinner(player)) {
            return "패";
        }
        if (player.isWinner(dealer)) {
            return "승";
        }
        return "무";
    }

    public Money calculateProfit(Player player, Money money) {
        if (gameResult.get(player).equals("승")) {
            return Money.profits(1, money);
        }
        if (gameResult.get(player).equals("패")) {
            return Money.profits(-1, money);
        }
        if (gameResult.get(player).equals("블랙잭")) {
            return Money.profits(1.5, money);
        }
        return Money.profits(0, money);
    }
}
