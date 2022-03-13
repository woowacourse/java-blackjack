package blackjack.domain.participant;

import blackjack.domain.result.Result;

public class User extends Participant{

    public User(String name) {
        super(name);
    }

    public Result checkResult(int otherScore) {
        return Result.checkUserResult(holdingCard.cardSum(), otherScore);
    }
}
