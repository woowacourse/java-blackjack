package blackjack.domain.participants;

import blackjack.domain.Response;
import blackjack.domain.ResultType;
import blackjack.domain.names.Name;

public class Player extends Participant {

    private final Betting betting;

    public Player(Name name, Betting betting) {
        super(name);
        this.betting = betting;
    }

    @Override
    protected ParticipantState updateStatus(ParticipantState currentStatus) {
        if (isBust()) {
            return ParticipantState.BUST;
        }
        return currentStatus;
    }

    public void updateStatusByResponse(Response response) {
        setState(response.getParticipantState());
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
