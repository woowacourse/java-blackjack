package blackjack.model.user;

import static blackjack.model.gameresult.GameResult.*;

import blackjack.model.gameresult.GameResult;

public class Player extends User {

    private static final int BLACKJACK_SCORE = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isHitAvailable() {
        return totalScore() < BLACKJACK_SCORE;
    }

    public GameResult judge(Dealer dealer) {
        if (this.isBust() || dealer.isBust()) {
            return judgeByBust();
        }

        if (this.isBlackjack() || dealer.isBlackjack()) {
            return judgeByBlackjack(dealer);
        }

        return judgeByScore(dealer);
    }

    private GameResult judgeByBust() {
        if (this.isBust()) {
            return LOSE;
        }

        return WIN;
    }

    private GameResult judgeByBlackjack(Dealer dealer) {
        if (this.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }

        if (this.isBlackjack()) {
            return BLACKJACK_WIN;
        }

        return LOSE;
    }

    private GameResult judgeByScore(Dealer dealer) {
        if (this.totalScore() > dealer.totalScore()) {
            return WIN;
        }

        if (this.totalScore() < dealer.totalScore()) {
            return LOSE;
        }

        return DRAW;
    }
}
