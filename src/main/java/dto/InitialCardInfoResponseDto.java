package dto;

import java.util.List;
import java.util.Map;

public record InitialCardInfoResponseDto(String dealerName,
                                         String dealerHandInfo,
                                         Map<String, List<String>> gamblersInfo) {
}
