package domain.dto;

import java.util.Map;

public record BlackjackResultDto(
        int winCount,
        int loseCount,
        Map<String, Boolean> matchResultMap
) {
}
