package dto;

import domain.WinningStatus;

import java.util.*;

public record FinalResultDto(List<ScoreResultDto> scoreResultDtos,
                             int dealerWinCount,
                             int dealerLoseCount,
                             Map<String, WinningStatus> playerResults) {
}
