package blackjack.dto;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.rule.BattingStatus;

public record PlayerWinningResultDto(String name, String winnStatus) {

    public static PlayerWinningResultDto of(final ParticipantName name, final BattingStatus battingStatus) {
        return new PlayerWinningResultDto(name.getName(), battingStatus.name());
    }
}
