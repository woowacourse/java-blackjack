package blackjack.dto;

import blackjack.domain.GameResult;

public record PlayerGameResult(
        String nickname,
        GameResult gameResult
) {
}
