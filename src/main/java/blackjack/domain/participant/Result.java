package blackjack.domain.participant;

import blackjack.domain.betting.Profit;
import java.util.function.Function;

public enum Result {

    WIN("승", profit -> profit),
    DRAW("무", profit -> profit.zero()),
    LOSE("패", profit -> profit.loss());

    private final String name;
    private final Function<Profit, Profit> function;

    Result(final String name, final Function<Profit, Profit> function) {
        this.name = name;
        this.function = function;
    }

    public Profit calculate(final Profit profit) {
        return function.apply(profit);
    }

    public Result reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
