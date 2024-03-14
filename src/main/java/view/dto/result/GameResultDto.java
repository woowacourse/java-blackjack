package view.dto.result;

import java.util.Map;

public record GameResultDto(Map<String, String> gameResult, Map<String, Integer> dealerResult) {
}
