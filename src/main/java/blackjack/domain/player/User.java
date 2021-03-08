package blackjack.domain.player;

import blackjack.domain.ResultType;

public class User extends AbstractPlayer {
    private static final String YES = "y";
    private static final String NO = "n";

    private boolean isDrawStop = false;
    private BetAmount betAmount;

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

    public void setBetAmount(String betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public int profit(Dealer dealer) {
        return getResult(dealer).profit(betAmount.getAmount());
    }

    public ResultType getResult(Dealer dealer) {
        int userScore = getScore();
        int dealerScore = dealer.getScore();
        if (isBlackJack() && !dealer.isBlackJack()) {
            return ResultType.BLACKJACK;
        }
        if (userScore > BLACKJACK || userScore < dealerScore) {
            return ResultType.LOSS;
        }
        if (userScore == dealerScore) {
            return ResultType.DRAW;
        }
        return ResultType.WIN;
    }
}