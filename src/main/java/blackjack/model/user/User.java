package blackjack.model.user;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import blackjack.model.gameresult.GameResult;
import java.util.List;

public abstract class User {

    private final Username name;
    private final Hand hand;

    public User(String name) {
        this.name = new Username(name);
        this.hand = new Hand();
    }

    public abstract boolean isHitAvailable();

    public abstract boolean isPlayer();

    public String getName() {
        return name.getName();
    }

    public List<Card> cards() {
        return hand.getCards();
    }

    public void draw(Card card) {
        hand.draw(card);
    }

    public void stay() {
        hand.stay();
    }

    public boolean isFinished() {
        return hand.isFinished();
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

    public GameResult judge(User user) {
        return hand.judge(user.hand);
    }
}
