package domain.dto;

import java.util.Map;

public record CardSumResponse(
        OpenCardsResponse openCardsResponse,
        Map<String, Integer> sum
) {
}
