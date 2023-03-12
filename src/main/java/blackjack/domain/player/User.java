package blackjack.domain.player;

import blackjack.domain.card.Card;

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

    public void updateCardScore(Card card) {
        handCards.updateCardScore(card);
    }

    public boolean isUnderBust() {
        return handCards.getTotalScore() <= BUST;
    }

    public String getName() {
        return name.getName();
    }

    public boolean isDealer(Object object) {
        return object instanceof Dealer;
    }
}
