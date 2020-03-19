package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static domain.util.NullValidator.validateNull;

public class BlackjackUserProfit {
    public static final double DEFAULT_PROFIT = 0.0;
    public static final double BLACKJACK_WIN_PROFIT_RATE = 1.5;
    public static final double BUST_WIN_PROFIT_RATE = 1.0;
    public static final double SCORE_WIN_PROFIT_RATE = 1.0;

    private final Map<String, Double> playerProfit;

    public BlackjackUserProfit(Players players, Dealer dealer) {
        validateNull(players, dealer);
        this.playerProfit = createPlayerProfit(players, dealer);
    }

    private Map<String, Double> createPlayerProfit(Players players, Dealer dealer) {
        Map<String, Double> playerProfit = new HashMap<>();

        for (Player player : players.get()) {
            double playerProfitSum = playerProfit.getOrDefault(player.getName(), DEFAULT_PROFIT);
            playerProfitSum += calculatePlayerProfit(player, dealer);
            playerProfit.put(player.getName(), playerProfitSum);
        }

        return playerProfit;
    }

    private double calculatePlayerProfit(Player player, Dealer dealer) {
        if (player.isWinner(dealer)) {
            return calculatePlayerGain(player);
        }
        if (dealer.isWinner(player)) {
            return calculatePlayerLoss(player);
        }
        return DEFAULT_PROFIT;
    }

    private double calculatePlayerGain(Player player) {
        if (player.isBlackjack()) {
            return player.getBettingMoney() * BLACKJACK_WIN_PROFIT_RATE;
        }
        if (player.isNotBust()) {
            return player.getBettingMoney() * BUST_WIN_PROFIT_RATE;
        }
        return player.getBettingMoney() * SCORE_WIN_PROFIT_RATE;
    }

    private double calculatePlayerLoss(Player player) {
        if (player.isBust()) {
            return -(player.getBettingMoney() * BUST_WIN_PROFIT_RATE);
        }
        return -(player.getBettingMoney() * SCORE_WIN_PROFIT_RATE);
    }

    public double calculateDealerProfitSum() {
        return playerProfit.values().stream()
                .mapToDouble(value -> -value)
                .sum();
    }

    public Map<String, Double> get() {
        return Collections.unmodifiableMap(playerProfit);
    }
}
