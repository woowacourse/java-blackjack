package blackjack.model;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final Rule rule;
    private final Players players;

    public Referee(final Rule rule, final Players players) {
        this.rule = rule;
        this.players = players;
    }

    public Map<String, Result> judgePlayerResult() {
        Map<String, Result> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), rule.calculateResult(player));
        }
        return result;
    }

    public Map<Result, Integer> judgeDealerResult() {
        EnumMap<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        for (Result playerResult : judgePlayerResult().values()) {
            dealerResult.merge(findOpposite(playerResult), 1, Integer::sum);
        }
        return dealerResult;
    }

    private Result findOpposite(final Result originResult) {
        if (originResult == Result.WIN) {
            return Result.LOSE;
        }
        if (originResult == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }
}
