package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public class GameRound {

    private final Dealer dealer;
    private final Map<Player, BettingAmount> bettingAmounts = new LinkedHashMap<>();

    public GameRound(Dealer dealer) {
        this.dealer = dealer;
    }

    public static GameRound start(Dealer dealer) {
        return new GameRound(dealer);
    }

    public void betting(Player player, double money) {
        bettingAmounts.put(player, BettingAmount.of(money));
    }

    public boolean loseIfBust(Player player) {
        return player.isBust();
    }

    public double getEndBettingMoney(Player player) {
        return bettingAmounts.get(player).end();
    }

    public boolean endGameIfBlackjack(Player player) {
        if (player.isBlackjack()) {
            bettingAmounts.put(player, bettingAmounts.get(player).blackjack());
            return true;
        }
        return false;
    }

    public boolean endGameIfBlackjack(Dealer dealer) {
        if (dealer.isBlackjack()) {
            bettingAmounts.keySet().stream()
                .filter(Gamer::isBlackjack)
                .forEach(player -> bettingAmounts.put(player, bettingAmounts.get(player).draw()));
            return true;
        }
        return false;
    }

    public boolean endGameIfDealerBust() {
        if (dealer.isBust()) {
            bettingAmounts.keySet().stream()
                .filter(player -> !player.isBust())
                .forEach(player -> bettingAmounts.put(player, bettingAmounts.get(player).win()));
            return true;
        }
        return false;
    }

    public void computeResult() {
        bettingAmounts.keySet().stream()
            .filter(player -> RoundResult.judgeResult(player, dealer) == RoundResult.WIN)
            .forEach(player -> bettingAmounts.put(player, bettingAmounts.get(player).win()));
    }

    public Map<Gamer, Double> getAllProfit() {
        Map<Gamer, Double> playerResults = bettingAmounts.keySet().stream()
            .collect(Collectors.toMap(
                player -> player,
                player -> bettingAmounts.get(player).getProfit()));
        double playersProfitSum = playerResults.values().stream()
            .mapToDouble(Double::doubleValue)
            .sum();

        Map<Gamer, Double> allProfit = new LinkedHashMap<>();
        allProfit.put(dealer, -playersProfitSum);
        allProfit.putAll(playerResults);
        return allProfit;
    }
}
