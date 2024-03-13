package blackjack.dto;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.player.PlayerBetAmount;
import blackjack.domain.player.PlayerName;

public record PlayerBetAmountDto(PlayerName name, BetAmount betAmount) {

    public PlayerBetAmount toDomain() {
        return new PlayerBetAmount(name, betAmount);
    }
}
