package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {
    protected Hand hand;
    protected Name name;

    public User(Name name) {
        this.hand = new Hand();
        this.name = name;
    }

    public List<Card> getPlayerCards() {
        return hand.getPlayerCards();
    }

    public int getTotalScore() {
        return hand.getTotalScore();
    }

    public void updateCardScore(Card card) {
        hand.addCard(card);
        hand.updateTotalScore();
    }

    public abstract boolean isUnderLimit();
}
