package blackjack.dto;

import blackjack.domain.betting.BetRevenue;
import blackjack.domain.participant.ParticipantName;

public record PlayerBetResultDto(String name, double revenue) {

    public static PlayerBetResultDto of(final ParticipantName name, final BetRevenue betRevenue) {
        return new PlayerBetResultDto(name.getName(), betRevenue.getValue());
    }
}
