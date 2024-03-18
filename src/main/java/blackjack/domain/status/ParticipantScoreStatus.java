package blackjack.domain.status;

import blackjack.domain.Score;

public class ParticipantScoreStatus {
    private final boolean isBlackjack;
    private final Score score;

    public ParticipantScoreStatus(final boolean isBlackjack, final Score score) {
        this.isBlackjack = isBlackjack;
        this.score = score;
    }

    public ParticipantScoreStatus(final boolean isBlackjack, final int score) {
        this(isBlackjack, new Score(score));
    }

    public boolean isScoreBiggerThan(final Score otherScore) {
        return this.score.isBiggerThan(otherScore);
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }

    public boolean isScoreBiggerThan(final ParticipantScoreStatus otherScoreStatus) {
        return this.score.isBiggerThan(otherScoreStatus.score);
    }
}
