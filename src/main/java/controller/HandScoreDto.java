package controller;

import domain.Participant;

public class HandScoreDto {
    private final HandDto handDto;
    private final int score;

    public HandScoreDto(Participant participant) {
        this.handDto = new HandDto(participant);
        this.score = participant.score();
    }

    public int score() {
        return score;
    }
}
