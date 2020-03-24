package blackjack.domain.result.outcome;

import blackjack.domain.participant.Player;
import blackjack.domain.result.ResultType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinOrLoseResultResolver implements ResultResolver<Player, ResultType, Map<ResultType, Long>> {

    @Override
    public ResultType showPlayerResult(Player player, ResultType type) {
        return type;
    }

    @Override
    public Map<ResultType, Long> computeDealerResult(List<IntegratedResult<Player, ResultType, Map<ResultType, Long>>> playerResults) {
        return playerResults.stream()
                .map(IntegratedResult::showPlayerResult)
                .map(ResultType::reverse)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
