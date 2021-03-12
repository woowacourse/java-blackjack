package blackjack.domain.participants;

import blackjack.domain.Response;
import blackjack.domain.names.Name;
import blackjack.domain.state.hitstrategy.DealerStrategy;
import blackjack.domain.state.hitstrategy.HitStrategy;

public class Dealer extends Participant {

    public static final int DEALER_LIMIT = 16;
    public static final String DEALER_NAME = "딜러";
    private static final HitStrategy HIT_STRATEGY = new DealerStrategy();

    public Dealer() {
        super(new Name(DEALER_NAME), HIT_STRATEGY);
    }

    @Override
    public void updateStateByResponse(Response response) {
        throw new IllegalArgumentException("응답을 할 수 없는 참가자 입니다.");
    }
}
