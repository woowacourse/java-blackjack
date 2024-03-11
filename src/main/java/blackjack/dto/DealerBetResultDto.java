package blackjack.dto;

import blackjack.domain.betting.BetRevenue;
import blackjack.domain.participant.ParticipantName;

public record DealerBetResultDto(String name, double revenue) {

    public static DealerBetResultDto of(final ParticipantName participantName, final BetRevenue betRevenue) {
        return new DealerBetResultDto(participantName.getName(), betRevenue.getValue());
    }
}
