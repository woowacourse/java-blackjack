package domain.participant;

import java.util.Objects;

public final class ParticipantScore {

    private static final int ACE_STANDARD_SCORE = 11;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private static final ParticipantScore defaultScore = new ParticipantScore(0);

    private final int score;

    private ParticipantScore(final int score) {
        this.score = score;
    }

    static ParticipantScore defaultOf() {
        return defaultScore;
    }

    static ParticipantScore scoreOf(final int score) {
        return new ParticipantScore(score);
    }

    ParticipantScore add(final ParticipantScore target) {
        return new ParticipantScore(this.score + target.score);
    }

    ParticipantScore processIncludeAce() {
        if (score <= ACE_STANDARD_SCORE) {
            return new ParticipantScore(score + ACE_BONUS_SCORE);
        }
        return this;
    }

    boolean checkGreaterThan(final ParticipantScore target) {
        return this.score > target.score;
    }

    boolean checkBust() {
        return this.score > BLACKJACK_SCORE;
    }

    boolean checkBlackJack() {
        return this.score == BLACKJACK_SCORE;
    }

    public int score() {
        return score;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof ParticipantScore)) {
            return false;
        }
        final ParticipantScore targetParticipantScore = (ParticipantScore) target;
        return score == targetParticipantScore.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
