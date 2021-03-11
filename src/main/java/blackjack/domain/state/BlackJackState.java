package blackjack.domain.state;

import blackjack.domain.ResultType;

public class BlackJackState extends Finished {

    private static final double BLACK_JACK_PROFIT_RATE = 1.5;

    @Override
    public double profitRate(ResultType match) {
        if (match == ResultType.TIE) {
            return super.profitRate(match);
        }
        return BLACK_JACK_PROFIT_RATE;
    }
}
