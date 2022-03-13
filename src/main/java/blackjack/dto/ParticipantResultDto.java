package blackjack.dto;

import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantResultDto {

    private final ParticipantDto participantDto;
    private final int score;

    private ParticipantResultDto(final ParticipantDto participantDto, final int score) {
        this.participantDto = participantDto;
        this.score = score;
    }

    public static ParticipantResultDto of(Participant participant) {
        return new ParticipantResultDto(ParticipantDto.of(participant), participant.getScore());
    }

    public String getName() {
        return participantDto.getName();
    }

    public List<CardDto> getCardDtos() {
        return participantDto.getCardDtos();
    }

    public int getScore() {
        return score;
    }
}
