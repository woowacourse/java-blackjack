package domain.result;

import domain.pariticipant.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static domain.result.MatchCase.*;

public record PlayersMatchResult(Map<Player, MatchCase> playerMatchResult) {

    public PlayersMatchResult {
        playerMatchResult = Map.copyOf(playerMatchResult);
    }

    public PlayersBettingProfit calculateBettingProfit() {
        Map<Player, Long> bettingResult = new LinkedHashMap<>();

        for (Player player : playerMatchResult.keySet()) {
            long profit = player.calculateBettingProfit(playerMatchResult.get(player));
            bettingResult.put(player, profit);
        }
        return new PlayersBettingProfit(bettingResult);
    }

    public DealerMatchResult calculateDealerMatchResult() {
        Map<MatchCase, Long> result =
                playerMatchResult.values().stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return new DealerMatchResult(
                result.getOrDefault(MatchCase.WIN, 0L).intValue(),
                result.getOrDefault(MatchCase.DRAW, 0L).intValue(),
                result.getOrDefault(MatchCase.LOSE, 0L).intValue()
        );
    }
}
