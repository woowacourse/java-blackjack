package dto;

import domain.Outcome;
import java.util.List;
import java.util.Map;

public record AllOutcomeDto(
        Map<Outcome, Integer> dealerResult,
        List<PlayerOutcomeDto> playerOutcomes
) {
}
