package dto;

import java.util.*;

public record FinalResultDto(List<ScoreResultDto> scoreResultDtos,
                             long dealerProfit,
                             Map<String, Long> playerResults) {
}
