package blackjack.domain.player;

import blackjack.domain.ResultType;

public class User extends AbstractPlayer {
    private static final String YES = "y";
    private static final String NO = "n";
    private boolean isDrawStop = false;

    public User(String name) {
        super(name);
    }

    public boolean isDrawStop() {
        return isDrawStop;
    }

    private void stopDraw() {
        isDrawStop = true;
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isOverBlackJack());
    }

    public boolean isDrawContinue(String input) {
        drawInputValidate(input);
        if (YES.equals(input)) {
            return true;
        }
        stopDraw();
        return false;
    }

    public ResultType getResult(Dealer dealer) {
        int userValue = getValue();
        int dealerValue = dealer.getValue();
        if (userValue > BLACKJACK || userValue < dealerValue) {
            return ResultType.LOSS;
        }
        if (userValue == dealerValue) {
            return ResultType.DRAW;
        }
        return ResultType.WIN;
    }

    private void drawInputValidate(String value) {
        if (!(YES.equals(value) || NO.equals(value))) {
            throw new IllegalArgumentException("입력은 y 또는 n만 가능합니다.");
        }
    }

    private boolean isOverBlackJack() {
        return getValue() > BLACKJACK;
    }
}
