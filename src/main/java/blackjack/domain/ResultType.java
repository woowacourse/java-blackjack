package blackjack.domain;

import java.util.function.IntUnaryOperator;

public enum ResultType {
    BLACK_JACK(money -> (int) (money * 1.5)),
    WIN(money -> money),
    LOSE(money -> -money),
    PUSH(money -> 0);

    private final IntUnaryOperator revenue;

    ResultType(final IntUnaryOperator revenue) {
        this.revenue = revenue;
    }

    public static ResultType getReverseType(ResultType type) {
        if (type == WIN || type == BLACK_JACK) {
            return LOSE;
        }
        if (type == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public int getRevenue(int bettingMoney) {
        return revenue.applyAsInt(bettingMoney);
    }
}
