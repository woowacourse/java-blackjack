package blackjack.model.result;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import java.util.EnumMap;
import java.util.Map;

public class Referee {
    private final Rule rule;

    public Referee(final Rule rule) {
        this.rule = rule;
    }

    public ResultCommand judgePlayerResult(final Player player) {
        return rule.calculateResult(player);
    }

    public Map<ResultCommand, Integer> judgeDealerResult(final Players players) {
        return players.stream()
                .map(this::judgePlayerResult)
                .collect(groupingBy(
                        this::changeToOppositeResultCommand,
                        () -> new EnumMap<>(ResultCommand.class),
                        reducing(0, i -> 1, Integer::sum)
                ));
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
