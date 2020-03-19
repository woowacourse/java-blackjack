package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Score;

public class PlayerWithScoreTwentyOne implements User {
    @Override
    public Score calculateScore() {
        return new Score(21);
    }
}