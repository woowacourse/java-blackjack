package blackjack.domain.result.user;

import blackjack.domain.playing.card.Score;
import blackjack.domain.playing.user.AbstractUser;
import blackjack.domain.playing.user.User;

public class BlackjackDealer implements User {
    @Override
    public Score calculateScore() {
        return new Score(21);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public boolean isBust() {
        return calculateScore().isOver(AbstractUser.BLACKJACK_SCORE_NUMBER);
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isOverScore(User other) {
        return calculateScore().isOver(other.calculateScore());
    }

    public boolean isUnderScore(User other) {
        return !isOverScore(other);
    }

    public boolean isSameScore(User other) {
        return calculateScore().equals(other.calculateScore());
    }
}
