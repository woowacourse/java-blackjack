package blackjack.dto;

import blackjack.domain.game.Score;
import blackjack.domain.participant.Participant;

public class ResultStateDto {

    private final Score score;
    private final int handSize;

    private ResultStateDto(final Participant participant) {
        this.score = participant.getScore();
        this.handSize = participant.handSize();
    }

    public static ResultStateDto from(final Participant participant) {
        return new ResultStateDto(participant);
    }

    public Score getScore() {
        return score;
    }

    public int getHandSize() {
        return handSize;
    }
}
