package blackjack.domain.result.user;

import blackjack.domain.playing.card.Score;

public class BustPlayer extends AbstractUserForTest {
    @Override
    public Score calculateScore() {
        return new Score(22);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
