package blackjack.domain.result.user;

import blackjack.domain.playing.card.Score;

public class ScoreTwentyDealer extends AbstractUserForTest {
    @Override
    public Score calculateScore() {
        return new Score(20);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
