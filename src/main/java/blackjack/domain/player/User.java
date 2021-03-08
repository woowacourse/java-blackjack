package blackjack.domain.player;

import blackjack.domain.ResultType;

public class User extends AbstractPlayer {
    private static final String YES = "y";
    private static final String NO = "n";
    private boolean isDrawStop = false;

    public User(String name) {
        super(name);
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isOverBlackJack());
    }

    public boolean isDrawStop() {
        return isDrawStop;
    }

    private boolean isOverBlackJack() {
        return getScore() > BLACKJACK;
    }

    public boolean isDrawContinue(String input) {
        drawInputValidate(input);
        if (YES.equals(input)) {
            return true;
        }
        stopDraw();
        return false;
    }

    private void drawInputValidate(String value) {
        if (!(YES.equals(value) || NO.equals(value))) {
            throw new IllegalArgumentException("입력은 y 또는 n만 가능합니다.");
        }
    }

    private void stopDraw() {
        isDrawStop = true;
    }

    public ResultType getResult(Dealer dealer) {
        int userScore = getScore();
        int dealerScore = dealer.getScore();
        if (userScore > BLACKJACK || userScore < dealerScore) {
            return ResultType.LOSS;
        }
        if (userScore == dealerScore && userScore != BLACKJACK) {
            return ResultType.DRAW;
        }
        return ResultType.WIN;
    }
}