package blackjack.domain.participant;

import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.ResultType;

import java.util.Objects;

import static blackjack.domain.participant.attribute.Money.NULL_RESULT_TYPE_ERR_MSG;

public class BettingPlayer extends Player {
    private final Money money;

    BettingPlayer(Name name, Money money) {
        super(name);
        Objects.requireNonNull(money);
        this.money = money;
    }

    public BettingPlayer(String name, int money) {
        super(name);
        this.money = new Money(money);
    }

    public Double computeProfit(ResultType type) {
        Objects.requireNonNull(type, NULL_RESULT_TYPE_ERR_MSG);
        return money.computeProfit(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BettingPlayer that = (BettingPlayer) o;
        return Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), money);
    }
}
