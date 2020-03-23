package blackjack.domain.result.user;

import blackjack.domain.card.Score;

public class ScoreEighteenDealer extends AbstractUserForTest {
    @Override
    public Score calculateScore() {
        return new Score(18);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
