package blackjack.dto;

import blackjack.domain.bet.BetRevenue;
import blackjack.domain.player.UserName;

public record PlayerBetResultDto(UserName name, BetRevenue revenue) {

    public String getName() {
        return name.getName();
    }
}
