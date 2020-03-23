package blackjack.domain.user;

import blackjack.domain.card.Score;

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
