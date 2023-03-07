package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class User {
    protected static final int BUST = 21;

    protected final Hand hand;

    public User() {
        this.hand = new Hand();
    }

    public List<Card> getPlayerCards() {
        return hand.getPlayerCards();
    }

    public int getTotalScore() {
        return hand.getTotalScore();
    }

    public void updateCardScore() {
        Deck deck = Deck.getInstance();
        hand.updateCardScore(deck.drawCard());
    }

    public boolean isUnderBust() {
        return hand.getTotalScore() <= BUST;
    }
}
