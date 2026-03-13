package domain.dto;

import java.util.Map;

public record BettingResultDto(int totalMoney, Map<String, Integer> bettingResult) {}
