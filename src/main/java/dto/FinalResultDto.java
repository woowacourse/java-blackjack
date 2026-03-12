package dto;

import java.util.*;

public record FinalResultDto(List<ScoreResultDto> scoreResultDtos,
                             int dealerWinCount,
                             int dealerLoseCount,
                             Map<String, Long> playerResults) {
}
