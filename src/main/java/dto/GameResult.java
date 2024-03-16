package dto;

import java.util.Map;

public record GameResult(Double dealerResult, Map<String, Double> playerResult) {
}
