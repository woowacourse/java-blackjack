package blackjack.domain.result;

import blackjack.domain.participant.Participant;

public class UserResult {

    private final String userName;
    private final Result result;

    public UserResult(Participant participant, int dealerScore) {
        this.userName = participant.getName();
        this.result = Result.checkUserResult(participant.getCardSum(), dealerScore);
    }

    public String getUserName() {
        return userName;
    }

    public String getResult() {
        return result.getName();
    }
}
