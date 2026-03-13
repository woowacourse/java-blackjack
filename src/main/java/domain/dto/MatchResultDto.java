package domain.dto;

import java.util.Map;

import domain.enums.MatchCase;

public record MatchResultDto(Map<MatchCase, Integer> dealerResult, Map<String, MatchCase> playerResult) {
}
