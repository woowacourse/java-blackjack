package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingSession {
    Map<Player, Bet> bets = new HashMap<>();
    Map<Player, Earning> earnings = new HashMap<>();

    public void bet(Player player, double betAmount) {
        bets.put(player, new Bet(betAmount));
    }

    public void calculateProfit(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            GameResult playerGameResult = GameResult.calculatePlayerGameResult(dealer, player);
            Earning earning = Earning.calculate(playerGameResult, player, bets.get(player));
            earnings.put(player, earning);
        }
    }

    public double getPlayerProfit(Player player) {
        return earnings.get(player).getAmount();
    }

    public double getDealerProfit() {
        return earnings.values().stream()
                .mapToDouble(value -> value.getAmount() * -1)
                .sum();
    }

    public double getEarnings(Player player) {
        return earnings.get(player).getAmount();
    }

    public double getBetAmount(Player player) {
        return bets.get(player).getAmount();
    }
}
