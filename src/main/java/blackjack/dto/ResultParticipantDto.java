package blackjack.dto;

import blackjack.domain.game.Score;
import blackjack.domain.participant.Participant;

public class ResultParticipantDto {

    private final Score score;
    private final int handSize;

    private ResultParticipantDto(final Participant participant) {
        this.score = participant.getScore();
        this.handSize = participant.handSize();
    }

    public static ResultParticipantDto from(final Participant participant) {
        return new ResultParticipantDto(participant);
    }

    public Score getScore() {
        return score;
    }

    public int getHandSize() {
        return handSize;
    }
}
