package blackjack.dto;

import blackjack.domain.game.Score;
import blackjack.domain.participant.Participant;

public class ResultDto {

    private final Score score;
    private final int handSize;

    private ResultDto(final Participant participant) {
        this.score = participant.getScore();
        this.handSize = participant.handSize();
    }

    public static ResultDto from(final Participant participant) {
        return new ResultDto(participant);
    }

    public Score getScore() {
        return score;
    }

    public int getHandSize() {
        return handSize;
    }
}
