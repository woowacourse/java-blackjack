package blackjack.dto;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DealerResultsDto {

    private final List<DealerResultDto> value;

    private DealerResultsDto(List<DealerResultDto> value) {
        this.value = value;
    }

    public static DealerResultsDto of(Players players, Dealer dealer) {
        Map<GameResult, Long> results = players.getValue()
                .stream()
                .collect(groupingBy(player -> dealer.decideResult(player),
                        () -> new EnumMap<>(GameResult.class), counting()));

        List<DealerResultDto> value = results.keySet()
                .stream()
                .map(gameResult -> DealerResultDto.of(gameResult.getValue(), Math.toIntExact(results.get(gameResult))))
                .collect(toList());

        return new DealerResultsDto(value);
    }

    public List<DealerResultDto> getValue() {
        return value;
    }
}
