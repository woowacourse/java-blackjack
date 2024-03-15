package view.dto.result;

import java.util.Map;

public record GameResultDto(Map<String, String> playerResult, Map<String, Integer> dealerResult) {
}
