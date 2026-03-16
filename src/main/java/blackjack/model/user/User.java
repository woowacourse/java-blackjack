package blackjack.model.user;

import static blackjack.model.gameresult.GameResult.BLACKJACK_WIN;
import static blackjack.model.gameresult.GameResult.DRAW;
import static blackjack.model.gameresult.GameResult.LOSE;
import static blackjack.model.gameresult.GameResult.WIN;

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

    public GameResult judge(User user) {
        if (this.isBust() || user.isBust()) {
            return judgeByBust();
        }

        if (this.isBlackjack() || user.isBlackjack()) {
            return judgeByBlackjack(user);
        }

        return judgeByScore(user);
    }

    public abstract boolean isHitAvailable();

    public abstract boolean isPlayer();

    private GameResult judgeByBust() {
        if (this.isBust()) {
            return LOSE;
        }

        return WIN;
    }

    private GameResult judgeByBlackjack(User user) {
        if (this.isBlackjack() && user.isBlackjack()) {
            return DRAW;
        }

        if (this.isBlackjack()) {
            return BLACKJACK_WIN;
        }

        return LOSE;
    }

    private GameResult judgeByScore(User user) {
        if (this.totalScore() > user.totalScore()) {
            return WIN;
        }

        if (this.totalScore() < user.totalScore()) {
            return LOSE;
        }

        return DRAW;
    }
}
