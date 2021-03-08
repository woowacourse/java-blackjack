package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.UserDrawContinue;

public class User extends AbstractPlayer {
    private boolean isDrawStop = false;

    public User(String name) {
        super(name);
    }

    public boolean isDrawStop() {
        return isDrawStop;
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isOverBlackJack());
    }

    private boolean isOverBlackJack() {
        return getScore() > BLACKJACK;
    }

    public boolean isDrawContinue(UserDrawContinue userDrawContinue) {
        if (userDrawContinue.isContinue()) {
            return true;
        }
        isDrawStop = true;
        return false;
    }

    public ResultType getResult(Dealer dealer) {
        int userScore = getScore();
        int dealerScore = dealer.getScore();
        if (userScore > BLACKJACK) {
            return ResultType.LOSS;
        }
        if (dealerScore > BLACKJACK) {
            return ResultType.WIN;
        }
        return notBustCase(userScore, dealerScore);
    }

    private ResultType notBustCase(int userScore, int dealerScore) {
        if (userScore == dealerScore) {
            return ResultType.DRAW;
        }
        if (userScore > dealerScore) {
            return ResultType.WIN;
        }
        return ResultType.LOSS;
    }
}
