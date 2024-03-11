package blackjack.dto;

import blackjack.domain.result.WinStatus;

public record WinningResultCountDto(String winStatus, Long count) {
    public static WinningResultCountDto of(final WinStatus winStatus, final Long count) {
        return new WinningResultCountDto(winStatus.name(), count);
    }
}
