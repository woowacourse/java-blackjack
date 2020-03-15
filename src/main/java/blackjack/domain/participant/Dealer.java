package blackjack.domain.participant;

import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResultType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    public static final int LOWER_BOUND = 16;
    private static final int FIRST_CARD = 1;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canGetMoreCard() {
        return computeScore() <= LOWER_BOUND;
    }

    public List<String> showFirstCard() {
        return showCards().subList(0, FIRST_CARD);
    }

    public DealerResult createDealerResult(List<PlayerResult> playerResults) {
        Map<ResultType, Integer> dealerResult = new HashMap<>();

        for (ResultType type : ResultType.values()) {
            int resultCount = type.countSameResultType(playerResults);
            dealerResult.put(type.reverse(), resultCount);      // 플레이어 승 = 딜러 패, 플레이어 패 = 딜러 승
        }

        return new DealerResult(name, dealerResult);
    }
}
