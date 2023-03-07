package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class User {
    public static final int BUST = 21;

    protected final Hand hand;
    protected final Name name;

    public User(Name name) {
        this.name = name;
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

    public String getName() {
        return name.getName();
    }
}
