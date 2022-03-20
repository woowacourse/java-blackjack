package blackjack.domain.gameresult;

import blackjack.domain.participant.Participant;
import blackjack.domain.result.UserResult;

public class UserGameResult {

    private final String userName;
    private final UserResult result;

    public UserGameResult(Participant participant, Participant dealer) {
        this.userName = participant.getName();
        this.result = UserResult.checkUserResult(participant, dealer);
    }

    public String getUserName() {
        return userName;
    }

    public String getResult() {
        return result.getName();
    }
}
