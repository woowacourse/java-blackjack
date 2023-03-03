package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.player.PlayerCards;

import java.util.List;

public abstract class User {
    PlayerCards playerCards;

    public User() {
        this.playerCards = new PlayerCards();
    }

    public List<Card> getPlayerCards() {
        return playerCards.getPlayerCards();
    }

    public int getTotalScore() {
        return playerCards.getTotalScore();
    }
    public abstract boolean isUnderLimit();
}
