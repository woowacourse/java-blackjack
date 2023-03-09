package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class User {
    public static final int BUST = 21;

    protected final HandCards handCards;
    protected final Name name;

    public User(Name name) {
        this.name = name;
        this.handCards = new HandCards();
    }

    public List<Card> getPlayerCards() {
        return handCards.getPlayerCards();
    }

    public int getTotalScore() {
        return handCards.getTotalScore();
    }

    public void updateCardScore() {
        Deck deck = Deck.getInstance();
        handCards.updateCardScore(deck.drawCard());
    }

    public boolean isUnderBust() {
        return handCards.getTotalScore() <= BUST;
    }

    public String getName() {
        return name.getName();
    }
}
