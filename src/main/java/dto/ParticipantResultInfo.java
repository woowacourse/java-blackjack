package dto;

import domain.participant.Participant;

import java.math.BigDecimal;

public record ParticipantResultInfo(
        String name,
        BigDecimal profit
) {
    public static ParticipantResultInfo of(Participant participant, BigDecimal profit) {
        return new ParticipantResultInfo(participant.name(), profit);
    }
}
