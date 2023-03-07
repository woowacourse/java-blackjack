package blackjack.domain.player;

import blackjack.domain.result.ResultType;
import java.util.List;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    private static final int MAXIMUM_POINT = 16;

    public ResultType judge(Player challenger) {
        int dealerPoint = this.getTotalPoint();
        int challengerPoint = challenger.getTotalPoint();
        if (isBust() && challenger.isBust()) {
            return ResultType.DRAW;
        }
        if (isBust() && !challenger.isBust()) {
            return ResultType.WIN;
        }
        if (challenger.isBust()) {
            return ResultType.LOSE;
        }
        return comparePoint(dealerPoint, challengerPoint);
    }

    private static ResultType comparePoint(int dealerPoint, int challengerPoint) {
        if (challengerPoint < dealerPoint) {
            return ResultType.LOSE;
        }
        if (challengerPoint > dealerPoint) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }

    @Override
    public Boolean canPick() {
        List<Integer> sumPossibility = holdingCards.getSums();
        return sumPossibility.stream()
                .anyMatch(sum -> sum <= MAXIMUM_POINT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
