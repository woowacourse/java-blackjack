package blackjack.dto;

import blackjack.domain.bet.BetRevenue;
import blackjack.domain.user.UserName;

public record PlayerBetResultDto(UserName name, BetRevenue revenue) {

    public String getName() {
        return name.getName();
    }
}
