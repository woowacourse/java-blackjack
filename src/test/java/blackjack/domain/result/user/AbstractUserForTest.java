package blackjack.domain.result.user;

import blackjack.domain.playing.card.Score;
import blackjack.domain.playing.user.AbstractUser;
import blackjack.domain.playing.user.User;

public abstract class AbstractUserForTest implements User {
    @Override
    public abstract Score calculateScore();

    @Override
    public abstract boolean isBlackjack();

    @Override
    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    @Override
    public boolean isBust() {
        return calculateScore().isOver(AbstractUser.BLACKJACK_SCORE_NUMBER);
    }

    @Override
    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public boolean isOverScore(User other) {
        return calculateScore().isOver(other.calculateScore());
    }

    @Override
    public boolean isUnderScore(User other) {
        return !isOverScore(other);
    }

    @Override
    public boolean isSameScore(User other) {
        return calculateScore().equals(other.calculateScore());
    }

}
