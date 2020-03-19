package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Score;

public interface User {

    Score calculateScore();

    boolean isBlackjack();

    boolean isNotBlackjack();

    boolean isBust();

    boolean isNotBust();

    boolean isOverScore(User other);

    boolean isUnderScore(User other);

    boolean isSameScore(User other);
}
