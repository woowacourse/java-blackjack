package blackjack.domain.participant;

import blackjack.domain.result.Result;
import blackjack.domain.result.UserResult;

public class User extends Participant {

    public User(String name) {
        super(name);
    }

    public ParticipantDto getUserInfo() {
        return ParticipantDto.of(name, holdingCards.getCards());
    }

    public ParticipantDto getUserInfoWithScore() {
        return ParticipantDto.of(name, holdingCards.getCards(), holdingCards.cardSum());
    }

    public UserResult getUserInfoWithResult(int dealerSum) {
        return new UserResult(name, checkResult(dealerSum));
    }

    private Result checkResult(int dealerSum) {
        return Result.checkUserResult(holdingCards.cardSum(), dealerSum);
    }
}
