package blackjack.domain.participants;

import blackjack.domain.ResultType;
import blackjack.domain.names.Name;
import blackjack.domain.state.hitstrategy.HitStrategy;
import blackjack.domain.state.hitstrategy.PlayerStrategy;

public class Player extends Participant {

    private static final HitStrategy HIT_STRATEGY = new PlayerStrategy();
    private final Betting betting;

    public Player(Name name, Betting betting) {
        super(name, HIT_STRATEGY);
        this.betting = betting;
    }

    public ResultType match(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSE;
        }
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        if (isBlackJack()) {
            return matchWhenBlackJack(dealer);
        }
        return ResultType.getResultTypeByScore(this, dealer);
    }

    private ResultType matchWhenBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return ResultType.TIE;
        }
        return ResultType.WIN;
    }

    public int matchForProfit(Dealer dealer) {
        double profitMultiplier =
            match(dealer).getProfitMultiplier() * getState().getEarningRate();
        return (int) (profitMultiplier * betting.unwrap());
    }
}
