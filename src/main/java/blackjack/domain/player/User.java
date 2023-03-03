package blackjack.domain.player;

import blackjack.domain.card.Card;

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

    public void updateCardScore(Card card) {
        playerCards.updateCardScore(card);
    }
}
