package presentation.dto;

import java.util.Map;

public record GameResult(
        Map<String, Integer> memberAmount
) {
}
