package blackjack.model.user;

import blackjack.model.card.Card;
import blackjack.model.Hand;
import java.util.List;

public abstract class User {

    private final Username name;
    private final Hand hand;

    public User(String name) {
        this.name = new Username(name);
        this.hand = new Hand();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> cards() {
        return hand.getCards();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int totalScore() {
        return hand.calculateTotalScore();
    }

    public abstract boolean isHitAvailable();
}
