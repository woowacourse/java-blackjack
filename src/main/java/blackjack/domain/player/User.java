package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {
    protected static final int BUST = 21;

    protected final PlayerCards playerCards;

    public User() {
        this.playerCards = new PlayerCards();
    }

    public List<Card> getPlayerCards() {
        return playerCards.getPlayerCards();
    }

    public int getTotalScore() {
        return playerCards.getTotalScore();
    }

    public void updateCardScore(Card card) {
        playerCards.updateCardScore(card);
    }

    public boolean isBust() {
        return playerCards.getTotalScore() > BUST;
    }
}
