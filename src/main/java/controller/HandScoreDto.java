package controller;

import domain.Participant;

public class HandScoreDto extends HandDto {
    private final int score;

    public HandScoreDto(Participant participant) {
        super(participant);
        this.score = participant.score();
    }

    public int score() {
        return score;
    }
}
