package blackjack.domain.participants;

import blackjack.domain.Response;
import blackjack.domain.ResultType;
import blackjack.domain.cards.Deck;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    protected ParticipantStatus updateStatus(ParticipantStatus currentStatus) {
        if (isBust()) {
            return ParticipantStatus.BUST;
        }
        return currentStatus;
    }

    public void updateStatusByResponse(Response response) {
        setStatus(response.getPlayerStatus());
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
