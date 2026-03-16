package domain.player;

import domain.betting.BettingAmount;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GamblersFactory {

    public static List<Gambler> createGamblers(Map<String, BettingAmount> gamblersInfo) {
        return gamblersInfo.entrySet().stream()
                .map(entry -> new Gambler(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
