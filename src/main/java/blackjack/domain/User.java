package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.player.Name;
import blackjack.domain.player.PlayerCards;

import java.util.List;

public class User {
    private static final int SCORE_LIMIT = 21;
    Name name;
    PlayerCards playerCards;

    public User(Name name, PlayerCards playerCards) {
        this.name = name;
        this.playerCards = playerCards;
    }

    public boolean isUnderScoreLimit() {
        return playerCards.getTotalScore() < SCORE_LIMIT;
    }

}
