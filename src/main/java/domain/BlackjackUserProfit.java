package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static domain.util.NullValidator.validateNull;

public class BlackjackUserProfit {
    private final Map<String, Double> playerProfit;

    public BlackjackUserProfit(Players players, Dealer dealer) {
        validateNull(players, dealer);
        this.playerProfit = createPlayerProfit(players, dealer);
    }

    private Map<String, Double> createPlayerProfit(Players players, Dealer dealer) {
        Map<String, Double> playerProfit = new HashMap<>();

        for (Player player : players.get()) {
            double playerProfitSum = calculatePlayerProfit(player, dealer);
            playerProfit.put(player.getName(), playerProfitSum);
        }

        return playerProfit;
    }

    private double calculatePlayerProfit(Player player, Dealer dealer) {
        PlayerBlackjackResult playerBlackjackResult = PlayerBlackjackResult.from(player, dealer);
        return player.getBettingMoney() * playerBlackjackResult.getProfitRate();
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
