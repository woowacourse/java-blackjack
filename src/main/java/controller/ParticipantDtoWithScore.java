package controller;

import domain.Participant;

public class ParticipantDtoWithScore extends ParticipantDto {
    private final int score;

    public ParticipantDtoWithScore(Participant participant) {
        super(participant);
        this.score = participant.score();
    }

    public int score() {
        return score;
    }
}
