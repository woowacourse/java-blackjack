package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public class GameRound {

    private final Map<Player, Integer> initialBettingMoney = new HashMap<>();
    private final Map<Player, Integer> finalBettingMoney = new HashMap<>();

    public void betting(Player player, int money) {
        initialBettingMoney.put(player, money);
        finalBettingMoney.put(player, 0);
    }

    public boolean loseIfBust(Player player) {
/*
        if (player.isBust()) {
            finalBettingMoney.put(player, 0);
            return true;
        }
        return false;
*/
        return player.isBust();
    }

    public int getFinalBettingMoney(Player player) {
        return finalBettingMoney.get(player);
    }

    public boolean endGameIfBlackjack(Player player) {
        if (player.isBlackjack()) {
            finalBettingMoney.put(player, (int)(initialBettingMoney.get(player) * 1.5));
            return true;
        }
        return false;
    }

    public boolean endGameIfBlackjack(Dealer dealer) {
        if (dealer.isBlackjack()) {
            initialBettingMoney.keySet().stream()
                .filter(Gamer::isBlackjack)
                .forEach(player -> finalBettingMoney.put(player, initialBettingMoney.get(player)));

            return true;
        }
        return false;
    }
}
