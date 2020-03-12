package blackjack.domain.user;

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
        if (super.computeSum() < super.getCards().UPPER_LIMIT) {
            return true;
        }
        return false;
    }

    public ResultType createResult(Dealer dealer) {
        this.resultType = ResultType.computeResult(super.computeSum(), dealer.computeSum());
        return resultType;
    }
}
