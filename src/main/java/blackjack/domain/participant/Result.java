package blackjack.domain.participant;

import blackjack.domain.betting.Betting;
import java.util.function.Function;

public enum Result {

    WIN("승", Betting::getValue),
    DRAW("무", profit -> 0),
    LOSE("패", profit -> profit.getValue() * -1);

    private final String name;
    private final Function<Betting, Integer> function;

    Result(final String name, final Function<Betting, Integer> function) {
        this.name = name;
        this.function = function;
    }

    public int calculateProfit(final Betting betting) {
        return function.apply(betting);
    }

    public String getName() {
        return name;
    }
}
