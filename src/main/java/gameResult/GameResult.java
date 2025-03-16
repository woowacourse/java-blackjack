package gameResult;

import participant.Dealer;
import participant.Player;
import participant.Players;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    public Map<Player, MatchResultType> calculatePlayersMatchResult(Players players, Dealer dealer) {
        Map<Player, MatchResultType> matchResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            MatchResultType matchResultType = MatchResultType.of(player, dealer);
            matchResult.put(player, matchResultType);
        }
        return matchResult;
    }

    public Map<MatchResultType, Long> calculateDealerMatchResult(Map<Player, MatchResultType> playerMatchResult) {
        HashMap<MatchResultType, Long> countingResult = playerMatchResult.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        HashMap::new,
                        Collectors.counting()));
        return countingResult.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().calculateReverseResult(),
                        entry -> countingResult.get(entry.getKey()),
                        (existing, replacement) -> existing,
                        HashMap::new));
    }
}
