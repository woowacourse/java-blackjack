package blackjack.dto;

import blackjack.domain.result.WinStatus;

public record WinningCountDto(String winStatus, Long count) {
    public static WinningCountDto of(final WinStatus winStatus, final Long count) {
        return new WinningCountDto(winStatus.getName(), count);
    }
}
