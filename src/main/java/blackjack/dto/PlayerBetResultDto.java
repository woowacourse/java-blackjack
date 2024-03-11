package blackjack.dto;

import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.player.PlayerName;

public record PlayerBetResultDto(String name, double revenue) {

    public static PlayerBetResultDto of(final PlayerName name, final BetRevenue betRevenue) {
        return new PlayerBetResultDto(name.getName(), betRevenue.value());
    }
}
