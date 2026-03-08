package blackjack.dto;

import blackjack.model.PlayerBlackjackResult;

public record PlayerResultDto(
    String participantName,
    PlayerBlackjackResult result
) {
}
