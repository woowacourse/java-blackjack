package blackjack.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResults {

    private final Map<Player, ResultStatus> result;

    public GameResults(Map<Player, ResultStatus> result) {
        this.result = result;
    }

    public Map<ResultStatus, Long> getDealerResult() {
        return result.values()
                .stream()
                .collect(Collectors.groupingBy(resultState -> resultState,
                        () -> new EnumMap<>(ResultStatus.class),
                        Collectors.counting()));
    }

    public Map<Player, ResultStatus> getResult() {
        return result;
    }
}
