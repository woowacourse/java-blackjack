package blackjack.service;

import blackjack.domain.PlayerResult;
import blackjack.domain.User.Betting;
import blackjack.domain.User.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class batchService {

    public static Map<String, Double> calculate(Map<Player, PlayerResult> statistics) {
        Map<String, Double> info = new LinkedHashMap<>();
        for (Map.Entry<Player, PlayerResult> entry : statistics.entrySet()) {
            Player player = entry.getKey();
            Betting betting = player.getBetting();
            PlayerResult result = entry.getValue();
            info.put(player.getName(), betting.calculate(result.getProfit()));
        }
        return info;
    }
}
