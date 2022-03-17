package blackjack.model.player.matcher;

import blackjack.model.player.Money;
import java.math.BigDecimal;
import java.util.Objects;

public class Result {
    private enum Status {
        WIN, LOSS, DRAW, BLACKJACK;

        private static final BigDecimal BLACKJACK_PROFIT_RATE =  new BigDecimal("1.5");

        Money profit(Money money) {
            if (this == Status.WIN) {
                return money;
            } else if (this == Status.LOSS) {
                return money.negate();
            } else if (this == Status.BLACKJACK) {
                return money.multiply(BLACKJACK_PROFIT_RATE);
            }
            return new Money(BigDecimal.ZERO);
        }
    }

    private final Status status;
    private final Money bettingMoney;

    private Result(Status status, Money bettingMoney) {
        this.status = status;
        this.bettingMoney = bettingMoney;
    }

    public Money profit() {
        return status.profit(bettingMoney);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Result result = (Result) o;
        return status == result.status && Objects.equals(bettingMoney, result.bettingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, bettingMoney);
    }

    public static Result win(Money bettingMoney) {
        return new Result(Status.WIN, bettingMoney);
    }

    public static Result loss(Money bettingMoney) {
        return new Result(Status.LOSS, bettingMoney);
    }

    public static Result draw(Money bettingMoney) {
        return new Result(Status.DRAW, bettingMoney);
    }

    public static Result blackjack(Money bettingMoney) {
        return new Result(Status.BLACKJACK, bettingMoney);
    }
}
