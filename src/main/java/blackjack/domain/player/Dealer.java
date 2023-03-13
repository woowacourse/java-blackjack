package blackjack.domain.player;

import blackjack.domain.result.Result;

public class Dealer extends Player {

    public static final String NAME = "딜러";
    public static final int MAXIMUM_POINT = 16;

    public Result judge(Player challenger) {
        if (isBust()) {
            return resultWhenDealerIsBust(challenger);
        }
        if (isBlackJack()) {
            return resultWhenDealerIsBlackJack(challenger);
        }
        return resultWhenDealerIsNormal(challenger);
    }

    private Result resultWhenDealerIsBust(Player challenger) {
        if (challenger.isBust()) {
            return Result.DRAW;
        }
        if (challenger.isBlackJack()) {
            return Result.BLACKJACK;
        }
        return Result.WIN;
    }

    private Result resultWhenDealerIsBlackJack(Player challenger) {
        if (challenger.isBlackJack()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private Result resultWhenDealerIsNormal(Player challenger) {
        if (challenger.isBust()) {
            return Result.LOSE;
        }
        if (challenger.isBlackJack()) {
            return Result.BLACKJACK;
        }
        return comparePoint(challenger);
    }

    private Result comparePoint(Player challenger) {
        int dealerPoint = this.getTotalPoint();
        int challengerPoint = challenger.getTotalPoint();
        if (challengerPoint < dealerPoint) {
            return Result.LOSE;
        }
        if (challengerPoint > dealerPoint) {
            return Result.WIN;
        }
        return Result.DRAW;
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
