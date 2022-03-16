package blackjack.domain;

import blackjack.domain.User.Betting;
import blackjack.domain.User.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class Settlement {

    private final Map<String, Double> batch;

    public Settlement(Map<String, Double> batch) {
        this.batch = new LinkedHashMap<>(batch);
    }

    public static Map<String, Double> calculate(Map<Player, PlayerResult> statistics) {
        Map<String, Double> info = new LinkedHashMap<>();
        for (Map.Entry<Player, PlayerResult> entry : statistics.entrySet()) {
            if (entry.getValue().equals(PlayerResult.BLACKJACK)) {
                Player player = entry.getKey();
                Betting betting = player.getBetting();
                info.put(player.getName(), betting.getBlackJackRevenue());
            }
            if (entry.getValue().equals(PlayerResult.WIN)) {
                Player player = entry.getKey();
                Betting betting = player.getBetting();
                info.put(player.getName(), betting.getAmount());
            }
            if (entry.getValue().equals(PlayerResult.LOSE)) {
                Player player = entry.getKey();
                Betting betting = player.getBetting();
                info.put(player.getName(), betting.getLoseRevenue());
            }
            if (entry.getValue().equals(PlayerResult.DRAW)) {
                Player player = entry.getKey();
                Betting betting = player.getBetting();
                info.put(player.getName(), betting.getDrawRevenue());
            }
        }
        return info;
    }
}
