package blackjack.dto;

import blackjack.domain.betting.BettingStatus;

public record DealerWinningResultDto(String winStatus, int count) {

    public static DealerWinningResultDto of(final BettingStatus bettingStatus, final Long count) {
        return new DealerWinningResultDto(bettingStatus.name(), count.intValue());
    }
}
