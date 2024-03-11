package blackjack.dto;

import blackjack.domain.player.bet.BetRevenue;

public record DealerBetResultDto(String name, double revenue) {

    public static DealerBetResultDto of(final String name, final BetRevenue betRevenue) {
        return new DealerBetResultDto(name, betRevenue.value());
    }
}
