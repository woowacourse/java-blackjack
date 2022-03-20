package blackjack.model.player.matcher;

import java.util.Objects;

public class Result {

    private final ResultStatus status;
    private final Money bettingMoney;

    private Result(ResultStatus status, Money bettingMoney) {
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
        return new Result(ResultStatus.WIN, bettingMoney);
    }

    public static Result loss(Money bettingMoney) {
        return new Result(ResultStatus.LOSS, bettingMoney);
    }

    public static Result draw(Money bettingMoney) {
        return new Result(ResultStatus.DRAW, bettingMoney);
    }

    public static Result blackjack(Money bettingMoney) {
        return new Result(ResultStatus.BLACKJACK, bettingMoney);
    }
}
