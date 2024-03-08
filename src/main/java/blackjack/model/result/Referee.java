package blackjack.model.result;

import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
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

    public Map<String, ResultCommand> judgePlayerResult() {
        Map<String, ResultCommand> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), rule.calculateResult(player));
        }
        return result;
    }

    public Map<ResultCommand, Integer> judgeDealerResult() {
        EnumMap<ResultCommand, Integer> dealerResult = new EnumMap<>(ResultCommand.class);
        for (ResultCommand playerResult : judgePlayerResult().values()) {
            dealerResult.merge(findOpposite(playerResult), 1, Integer::sum);
        }
        return dealerResult;
    }

    private ResultCommand findOpposite(final ResultCommand originResult) {
        if (originResult == ResultCommand.WIN) {
            return ResultCommand.LOSE;
        }
        if (originResult == ResultCommand.LOSE) {
            return ResultCommand.WIN;
        }
        return ResultCommand.DRAW;
    }
}
