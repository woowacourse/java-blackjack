package blackjack.domain.result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

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

    public Stream<PlayerResult> stream() {
        return this.playerResults.stream();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayersResults that = (PlayersResults) o;
        return Objects.equals(playerResults, that.playerResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerResults);
    }
}
