package blackjack.domain.participant;

import blackjack.domain.betting.Betting;
import java.util.function.Function;

public enum Result {

    WIN("승", profit -> profit),
    DRAW("무", profit -> profit),
    LOSE("패", profit -> profit);

    private final String name;
    private final Function<Betting, Betting> function;

    Result(final String name, final Function<Betting, Betting> function) {
        this.name = name;
        this.function = function;
    }

    public Betting calculate(final Betting betting) {
        return function.apply(betting);
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
