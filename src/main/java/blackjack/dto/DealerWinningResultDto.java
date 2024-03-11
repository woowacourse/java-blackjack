package blackjack.dto;

import blackjack.domain.rule.BattingStatus;

public record DealerWinningResultDto(String winStatus, int count) {

    public static DealerWinningResultDto of(final BattingStatus battingStatus, final Long count) {
        return new DealerWinningResultDto(battingStatus.name(), count.intValue());
    }
}
