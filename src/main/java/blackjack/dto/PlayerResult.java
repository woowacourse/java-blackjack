package blackjack.dto;

import blackjack.domain.GameResult;

public record PlayerResult(
        String nickname,
        GameResult gameResult
) {
}
