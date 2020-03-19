package blackjack.domain.participant;

import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResultType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    static final String DEALER_NAME = "딜러";
    public static final int LOWER_BOUND = 16;
    private static final int FIRST_CARD = 1;

    public Dealer() {
        super(new Name(DEALER_NAME));
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
            ResultType reverseType = type.reverse();

            if (dealerResult.containsKey(reverseType)) {
                int count = dealerResult.get(reverseType);
                dealerResult.put(reverseType, count + resultCount);
                continue;
            }

            dealerResult.put(reverseType, resultCount);      // 플레이어 승 = 딜러 패, 플레이어 패 = 딜러 승, 플레이어 블랙잭 = 딜러 패
        }

        return new DealerResult(name, dealerResult);
    }
}
