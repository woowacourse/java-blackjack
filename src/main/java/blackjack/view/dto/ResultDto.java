package blackjack.view.dto;

import blackjack.model.BlackjackResult;

public record ResultDto(
        String playerName,
        BlackjackResult result
) {
}
