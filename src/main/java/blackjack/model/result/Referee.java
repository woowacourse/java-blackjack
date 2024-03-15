package blackjack.model.result;

import blackjack.model.participant.Players;
import blackjack.model.participant.Player;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final ResultRule resultRule;
    private final Players players;

    public Referee(final ResultRule resultRule, final Players players) {
        this.resultRule = resultRule;
        this.players = players;
    }

    public Map<Player, ResultCommand> judgePlayerResult() {
        Map<Player, ResultCommand> result = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            result.put(player, resultRule.calculateResult(player));
        }
        return result;
    }

    public Map<ResultCommand, Integer> judgeDealerResult() {
        Map<ResultCommand, Integer> dealerResult = new EnumMap<>(ResultCommand.class);
        for (ResultCommand playerResult : judgePlayerResult().values()) {
            dealerResult.merge(changeToOppositeResultCommand(playerResult), 1, Integer::sum);
        }
        return dealerResult;
    }

    private ResultCommand changeToOppositeResultCommand(final ResultCommand originResult) {
        if (originResult == ResultCommand.WIN) {
            return ResultCommand.LOSE;
        }
        if (originResult == ResultCommand.LOSE) {
            return ResultCommand.WIN;
        }
        return ResultCommand.DRAW;
    }
}
