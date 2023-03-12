package blackjack.domain.participant;

import blackjack.domain.betting.Betting;
import java.util.function.Function;

public enum Result {

    BLACK_JACK_WIN(betting -> (int) (betting.getValue() * 1.5)),
    WIN(Betting::getValue),
    DRAW(betting -> 0),
    LOSE(betting -> betting.getValue() * -1);

    private final Function<Betting, Integer> function;

    Result(final Function<Betting, Integer> function) {
        this.function = function;
    }

    public int calculateProfit(final Betting betting) {
        return function.apply(betting);
    }
}
