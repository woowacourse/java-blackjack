package blackjack.dto;

import blackjack.domain.Participant;

public class ParticipantEntireStatusDTO {
    private final ParticipantStatusDTO statusDTO;
    private final int score;

    private ParticipantEntireStatusDTO(final ParticipantStatusDTO statusDTO, final int score) {
        this.statusDTO = statusDTO;
        this.score = score;
    }

    public static ParticipantEntireStatusDTO of(final Participant participant) {
        return new ParticipantEntireStatusDTO(ParticipantStatusDTO.of(participant), participant.getTotalPoint());
    }

    public ParticipantStatusDTO getStatusDTO() {
        return statusDTO;
    }

    public int getScore() {
        return score;
    }
}
