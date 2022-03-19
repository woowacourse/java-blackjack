package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public enum UserProfit {
    WIN(Result.WIN, money -> money),
    WIN_WITH_BLACKJACK(Result.WIN, money -> (int) (money * 1.5)),
    DRAW(Result.DRAW, money -> 0),
    LOSE(Result.LOSE, money -> -money);

    private final Result result;
    private final IntUnaryOperator profitCalculator;

    UserProfit(Result result, IntUnaryOperator profitCalculator) {
        this.result = result;
        this.profitCalculator = profitCalculator;
    }

    public static int calculateMoney(Result result, boolean isBlackJack, int money) {
        UserProfit target = getTarget(result, isBlackJack);
        return target.calculate(money);
    }

    private static UserProfit getTarget(Result result, boolean isBlackJack) {
        if (result.isWin()) {
            return checkBlackJack(isBlackJack);
        }
        return Arrays.stream(values())
                .filter(userProfit -> userProfit.result == result)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    private static UserProfit checkBlackJack(boolean isBlackJack) {
        if (isBlackJack) {
            return WIN_WITH_BLACKJACK;
        }
        return WIN;
    }

    private int calculate(int money) {
        return profitCalculator.applyAsInt(money);
    }
}
