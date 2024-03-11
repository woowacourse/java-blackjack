package blackjack.dto;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.betting.BettingStatus;

public record PlayerWinningResultDto(String name, String winnStatus) {

    public static PlayerWinningResultDto of(final ParticipantName name, final BettingStatus bettingStatus) {
        return new PlayerWinningResultDto(name.getName(), bettingStatus.name());
    }
}
