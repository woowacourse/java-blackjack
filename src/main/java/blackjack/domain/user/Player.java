package blackjack.domain.user;

import blackjack.domain.result.Result;


public class Player extends User{
    private Result result;

    public Result getResult() {
        return result;
    }

    @Override
    public boolean canReceiveMoreCard() {
        if (super.computeSum() > super.getCards().UPPER_LIMIT) {
            return true;
        }
        return false;
    }
}
