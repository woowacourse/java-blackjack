package blackjack.domain.player;

import blackjack.domain.result.ResultType;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    public static final int MAXIMUM_POINT = 16;

    public ResultType judge(Player challenger) {
        if (isBust() && challenger.isBust()) {
            return ResultType.DRAW;
        }
        if (isBust() && !challenger.isBust()) {
            return ResultType.WIN;
        }
        if (challenger.isBust()) {
            return ResultType.LOSE;
        }
        return comparePoint(challenger);
    }

    private ResultType comparePoint(Player challenger) {
        int dealerPoint = this.getTotalPoint();
        int challengerPoint = challenger.getTotalPoint();
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
        return holdingCards.getSum() <= MAXIMUM_POINT;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
