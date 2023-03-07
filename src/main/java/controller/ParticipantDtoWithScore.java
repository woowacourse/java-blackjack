package controller;

import domain.Participant;
import java.util.List;

public class ParticipantDtoWithScore {

    private final ParticipantDto participantDto;
    private final int score;

    public ParticipantDtoWithScore(Participant participant) {
        this.participantDto = new ParticipantDto(participant);
        this.score = participant.score();
    }

    public String name() {
        return participantDto.name();
    }

    public List<String> cards() {
        return participantDto.cards();
    }

    public int score() {
        return score;
    }
}
