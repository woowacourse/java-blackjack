package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {
    protected PlayerCards playerCards;
    protected Name name;

    public User(Name name) {
        this.playerCards = new PlayerCards();
        this.name = name;
    }

    public List<Card> getPlayerCards() {
        return playerCards.getPlayerCards();
    }

    public int getTotalScore() {
        return playerCards.getTotalScore();
    }

    public void updateCardScore(Card card) {
        playerCards.addCard(card);
        playerCards.updateTotalScore();
    }

    public abstract boolean isUnderLimit();
}
