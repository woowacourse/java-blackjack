package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.round.RoundResult;

import java.util.LinkedHashMap;
import java.util.List;

public class PlayerBets {

    private final LinkedHashMap<Gamer, Integer> bets = new LinkedHashMap<>();

    public void add(Gamer gamer, int amount) {
        bets.put(gamer, amount);
    }

    public double getPlayerProfit(Player player, Dealer dealer) {
        int playerBet = bets.get(player);
        RoundResult roundResult = RoundResult.judgeResult(player, dealer);
        if (roundResult == RoundResult.TIE) {
            return 0;
        }
        if (roundResult == RoundResult.LOSE) {
            return -playerBet;
        }
        if (roundResult == RoundResult.WIN && player.isBlackjack()) {
            return (double) playerBet * 1.5;
        }
        return playerBet;
    }

    public double getDealerProfit(Dealer dealer, List<Player> players) {
        double totalProfit = 0;
        for (Player player : players) {
            RoundResult roundResult = RoundResult.judgeResult(dealer, player);
            if (roundResult == RoundResult.LOSE && player.isBlackjack()) {
                totalProfit -= (bets.get(player) * 1.5);
            }
            if (roundResult == RoundResult.LOSE) {
                totalProfit -= bets.get(player);
            }
            if (roundResult == RoundResult.WIN) {
                totalProfit += bets.get(player);
            }
        }
        return totalProfit;
    }
}
