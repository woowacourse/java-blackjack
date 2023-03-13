package controller;

import domain.Participant;

import java.util.List;

public class HandScoreDto {
    private final HandDto handDto;
    private final int score;

    public HandScoreDto(final Participant participant) {
        this.handDto = new HandDto(participant);
        this.score = participant.score();
    }

    public int score() {
        return score;
    }

    public String name() {
        return handDto.name();
    }

    public List<String> cards() {
        return handDto.cards();
    }
}
