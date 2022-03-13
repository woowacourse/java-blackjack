package blackjack.dto;

import blackjack.domain.participant.Participant;

public class ParticipantResultDto {

    private final ParticipantDto participantDto;
    private final int score;

    private ParticipantResultDto(final ParticipantDto participantDto, final int score) {
        this.participantDto = participantDto;
        this.score = score;
    }

    public static ParticipantResultDto from(Participant participant) {
        return new ParticipantResultDto(ParticipantDto.from(participant), participant.getScore());
    }

    public ParticipantDto getParticipantDto() {
        return participantDto;
    }

    public int getScore() {
        return score;
    }
}
