package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.profit.ProfitResult;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public record ProfitResultDto(Map<String, Integer> result) {

    public static ProfitResultDto from(final ProfitResult profitResult) {
        final Map<Participant, Integer> result = profitResult.getResult();

        return result.keySet()
                .stream()
                .collect(collectingAndThen(
                        toMap(Participant::getName, result::get, (x, y) -> y, LinkedHashMap::new),
                        ProfitResultDto::new));
    }
}
