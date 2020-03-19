package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Score;

public interface User {
    Score calculateScore();
}
