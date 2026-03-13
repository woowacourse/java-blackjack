package domain.dto;

import java.util.Map;

import domain.MatchCase;

public record MatchResultDto(Map<MatchCase, Integer> dealerResult, Map<String, MatchCase> playerResult) {
}
