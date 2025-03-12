package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.round.RoundResult;

import java.util.LinkedHashMap;
import java.util.List;

public class PlayerBets {

    private static final int MIN_BET = 1;
    private static final int MAX_BET = 100_000_000;

    private final LinkedHashMap<Gamer, Integer> bets = new LinkedHashMap<>();

    public void add(Gamer gamer, int amount) {
        validateRange(amount);
        bets.put(gamer, amount);
    }

    private void validateRange(int amount) {
        if (amount < MIN_BET) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 이상의 수를 입력해 주세요.", MIN_BET));
        }
        if (amount > MAX_BET) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 이하의 수를 입력해 주세요.", MAX_BET));
        }
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
