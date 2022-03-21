package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<String, Integer> profits;

    public GameResult(Map<String, Integer> profits) {
        this.profits = Collections.unmodifiableMap(new LinkedHashMap<>(profits));
    }

    public static GameResult of(Dealer dealer, Players players) {
        Map<String, Integer> playersProfits = new LinkedHashMap<>();

        for (Player player : players) {
            Outcome outcome = Outcome.judge(player, dealer);
            playersProfits.put(player.getName(), player.calculateProfit(outcome));
        }

        return new GameResult(playersProfits);
    }

    public int getDealerProfit() {
        return profits.values()
                .stream()
                .mapToInt(profit -> profit * (-1))
                .sum();
    }

    public Map<String, Integer> getProfits() {
        return profits;
    }
}
