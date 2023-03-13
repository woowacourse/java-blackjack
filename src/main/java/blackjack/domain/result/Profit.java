package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Profit {
    private static final int NEGATIVE = -1;

    private final Map<String, Double> value;

    private Profit(Map<String, Double> value) {
        this.value = value;
    }

    public static Profit of(Dealer dealer, Players players) {
        Map<String, Double> value = players.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> player.calculateProfit(dealer),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        return new Profit(value);
    }

    public double calculateDealerScore() {
        return value.values().stream()
                .mapToDouble(value -> value)
                .sum() * NEGATIVE;
    }

    public double getPlayerProfit(String playerName) {
        return value.get(playerName);
    }
}
