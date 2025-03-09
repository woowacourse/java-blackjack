package blackjack.dto;

import blackjack.domain.game.WinningType;

public record PlayerWinningResult(
        String nickname,
        WinningType winningType
) {

}
