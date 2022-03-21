package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public enum UserProfit {
    WIN(UserResult.WIN, money -> money),
    WIN_WITH_BLACKJACK(UserResult.WIN, money -> (int) (money * 1.5)),
    DRAW(UserResult.DRAW, money -> 0),
    LOSE(UserResult.LOSE, money -> -money);

    private final UserResult result;
    private final IntUnaryOperator profitCalculator;

    UserProfit(UserResult result, IntUnaryOperator profitCalculator) {
        this.result = result;
        this.profitCalculator = profitCalculator;
    }

    public static int calculateMoney(UserResult result, boolean isBlackJack, int money) {
        UserProfit target = findProfitType(result, isBlackJack);
        return target.calculate(money);
    }

    private static UserProfit findProfitType(UserResult result, boolean isBlackJack) {
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
