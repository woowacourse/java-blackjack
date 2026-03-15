package domain.member;

import domain.vo.RoundResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Settler {

    public Map<Player, Integer> getPlayerProfits(Dealer dealer, List<Player> players) {
        return judgeGameResults(dealer, players).entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getKey()
                                .calculateProfit(entry.getValue()),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public int getDealerProfit(Dealer dealer, List<Player> players) {
        return getPlayerProfits(dealer, players).values().stream()
                .mapToInt(result -> -result)
                .sum();
    }

    private Map<Player, RoundResult> judgeGameResults(Dealer dealer, List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                                player -> player,
                                player -> RoundResult.judgeAgainst(
                                        dealer,
                                        player
                                ),
                                (existing, replacement) -> existing,
                                LinkedHashMap::new
                        )
                );
    }
}
