package blackjack.dto;

import blackjack.model.result.PlayerResult;

public record PlayerResultDto(String name, PlayerResult playerResult) {
}
