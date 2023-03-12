package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {
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

    public abstract void updateCardScore(Card card);

    public boolean isBust() {
        return handCards.isBust();
    }

    public String getName() {
        return name.getName();
    }

    public abstract boolean isDealer();

    public boolean isBlackjack() {
        return handCards.isBlackjack();
    }
}
