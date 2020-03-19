package blackjack.domain.result;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

// TODO: 2020-03-19 Betting게임인 경우만 computeDealerScore 동작할 수 있는 방법으로 변경
public class PlayersResults {
    private static final String DELIMITER = " ";

    private final List<PlayerResult> playerResults;

    public PlayersResults(List<PlayerResult> playerResults) {
        Objects.requireNonNull(playerResults, NULL_ERR_MSG);
        this.playerResults = playerResults;
    }

    public String showDealerRecord() {
        Map<ResultType, Long> dealerResult = computeDealerResult();

        return Arrays.stream(ResultType.values())
                .filter(dealerResult::containsKey)
                .map(type -> dealerResult.get(type) + type.getWord())
                .collect(Collectors.joining(DELIMITER));
    }

    private Map<ResultType, Long> computeDealerResult() {
        return playerResults.stream()
                .map(PlayerResult::getResultType)
                .map(ResultType::reverse)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public double computeDealerProfit() {
        return -playerResults.stream()
                .mapToDouble(PlayerResult::computeProfit)
                .sum();
    }

    public List<PlayerResult> getPlayersResults() {
        return Collections.unmodifiableList(playerResults);
    }
}
