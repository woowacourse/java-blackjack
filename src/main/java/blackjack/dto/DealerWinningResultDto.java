package blackjack.dto;

import blackjack.domain.result.WinStatus;

public record DealerWinningResultDto(String winStatus, int count) {

    public static DealerWinningResultDto of(final WinStatus winStatus, final Long count) {
        return new DealerWinningResultDto(winStatus.name(), count.intValue());
    }
}
