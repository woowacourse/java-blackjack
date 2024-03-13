package blackjack.dto;

import blackjack.domain.bet.BetRevenue;
import blackjack.domain.player.PlayerName;

public record PlayerBetResultDto(PlayerName name, BetRevenue revenue) {

    public String getName() {
        return name.getName();
    }
}
