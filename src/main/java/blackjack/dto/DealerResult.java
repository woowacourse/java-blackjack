package blackjack.dto;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    private final Map<GameResult, Long> results;

    public DealerResult(Players players, Dealer dealer) {
        this.results = players.getValue()
                .stream()
                .collect(groupingBy(dealer::decideResult, this::createResults, counting()));
    }

    private EnumMap<GameResult, Long> createResults() {
        return new EnumMap<>(GameResult.class);
    }

    public Map<GameResult, Long> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
