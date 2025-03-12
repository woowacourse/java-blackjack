package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.round.RoundResult;

import java.util.LinkedHashMap;

public class PlayerBets {

    private LinkedHashMap<Gamer, Integer> bets = new LinkedHashMap();

    public void add(Gamer gamer, int amount) {
        bets.put(gamer, amount);
    }

    public double getPlayerProfit(Player player, Dealer dealer) {
        int playerBet = bets.get(player);
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);
        if (roundResult == RoundResult.WIN && player.isBlackjack()) {
            return (double) playerBet * 1.5;
        }
        return playerBet;
    }
}
