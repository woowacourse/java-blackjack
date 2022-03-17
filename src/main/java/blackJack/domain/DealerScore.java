package blackJack.domain;

import blackJack.domain.User.Player;
import blackJack.domain.User.Players;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DealerScore {
    Map<Result, Integer> dealerScore;

    public DealerScore() {
        dealerScore = new EnumMap<Result, Integer>(Result.class);
        for (Result value : Result.values()) {
            dealerScore.put(value,0);
        }
    }

    public void addResult(Result result, Integer count) {
        dealerScore.put(result, count);
    }

    private int getDealerResultCount(Result result, List<Result> results) {
        return (int) results.stream().filter((r) -> r.equals(result)).count();
    }

    public Map<Result, Integer> makeDealerResult(PlayerScore playerScore) {
        List<Result> results = playerScore.getPlayersResult().values().stream().collect(Collectors.toUnmodifiableList());
        for (Result value : Result.values()) {
            addResult(value, getDealerResultCount(Result.reverse(value), results));
        }
        return dealerScore;
    }

    public Map<Result, Integer> getDealerScore() {
        return dealerScore;
    }

    public void makeBlackjackResult(Players players) {
         addResult(Result.WIN, players.getPlayers().size());
    }
}
