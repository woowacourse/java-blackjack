package blackjack.view.dto;

import blackjack.model.game.BlackjackResult;

public record ResultDto(
        String playerName,
        BlackjackResult result,
        double profit
) {
}
