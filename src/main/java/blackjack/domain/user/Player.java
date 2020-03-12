package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.result.ResultType;

public class Player extends User {
    private ResultType resultType;

    public Player(String name) {
        super(name);
    }

    public ResultType getResultType() {
        return resultType;
    }

    @Override
    public boolean canReceiveMoreCard() {
        return super.computeSum() <= Cards.UPPER_LIMIT;
    }

    public ResultType createResult(Dealer dealer) {
        this.resultType = ResultType.computeResult(super.computeSum(), dealer.computeSum());
        return resultType;
    }

    public String getResultTypeMessage() {
        return resultType.getMessage();
    }
}
