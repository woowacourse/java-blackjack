package blackjack.model.result;

import blackjack.model.participant.Participants;
import blackjack.model.participant.Player;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private final Rule rule;
    private final Participants participants;

    public Referee(final Rule rule, final Participants participants) {
        this.rule = rule;
        this.participants = participants;
    }

    public Map<Player, ResultCommand> judgePlayerResult() {
        Map<Player, ResultCommand> result = new LinkedHashMap<>();
        for (Player player : participants.getPlayers()) {
            result.put(player, rule.calculateResult(player));
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
