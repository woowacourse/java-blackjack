package blackjack.dto;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;

public record PlayerWinningResultDto(String playerName, String winStatus) {
    public static PlayerWinningResultDto of(final ParticipantName name, final WinStatus winStatus) {
        return new PlayerWinningResultDto(name.getName(), winStatus.name());
    }
}
