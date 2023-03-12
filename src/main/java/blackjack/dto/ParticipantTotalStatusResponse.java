package blackjack.dto;

import blackjack.domain.participant.Participant;

public class ParticipantTotalStatusResponse {
    private final ParticipantStatusResponse participantStatusResponse;
    private final int score;

    public ParticipantTotalStatusResponse(ParticipantStatusResponse participantStatusResponse, int score) {
        this.participantStatusResponse = participantStatusResponse;
        this.score = score;
    }

    public static ParticipantTotalStatusResponse of(Participant participant) {
        return new ParticipantTotalStatusResponse(ParticipantStatusResponse.of(participant),
                participant.getScore().getScore());
    }

    public ParticipantStatusResponse getParticipantStatusResponse() {
        return participantStatusResponse;
    }

    public int getScore() {
        return score;
    }
}
