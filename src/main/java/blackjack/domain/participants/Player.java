package blackjack.domain.participants;

import blackjack.domain.Response;
import blackjack.domain.ResultType;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    protected ParticipantState updateStatus(ParticipantState currentStatus) {
        if (isBust()) {
            return ParticipantState.BUST;
        }
        return currentStatus;
    }

    public void updateStatusByResponse(Response response) {
        setState(response.getPlayerStatus());
    }

    public ResultType match(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSE;
        }
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return ResultType.getResultTypeByScore(this, dealer);
    }

}
