package domain.participant.dto;

import domain.participant.Participant;

public record ParticipantResult(ParticipantHand playerHand, int resultScore) {
    public static ParticipantResult from(Participant participant) {
        int resultScore = participant.getResultScore();
        return new ParticipantResult(ParticipantHandMapper.map(participant), resultScore);
    }
}
