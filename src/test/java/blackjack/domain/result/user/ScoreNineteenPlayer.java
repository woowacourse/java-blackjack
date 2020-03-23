package blackjack.domain.result.user;

import blackjack.domain.card.Score;

public class ScoreNineteenPlayer extends AbstractUserForTest {
    @Override
    public Score calculateScore() {
        return new Score(19);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
