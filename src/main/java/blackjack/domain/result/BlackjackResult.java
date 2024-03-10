package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class BlackjackResult {
    private final Map<Player, HandResult> playersResult;

    public BlackjackResult(Map<Player, HandResult> playersResult) {
        this.playersResult = Collections.unmodifiableMap(playersResult);
    }

    public Map<HandResult, Integer> getDealerResults() {
        return playersResult.values()
                .stream()
                .map(HandResult::getOpposite)
                .sorted(Comparator.comparingInt(HandResult::ordinal))
                .collect(groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        collectingAndThen(counting(), Long::intValue)
                ));
    }

    public Map<Player, HandResult> getPlayersResult() {
        return playersResult;
    }
}
