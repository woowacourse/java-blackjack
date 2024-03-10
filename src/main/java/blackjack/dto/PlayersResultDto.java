package blackjack.dto;

import java.util.Map;

public record PlayersResultDto(
        Map<String, String> playerResult) {
}
