package dto;

import java.util.List;

public record BlackJackResultResponse(String nickname, List<String> cardNames, int totalScore) {
}
