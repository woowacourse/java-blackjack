package domain;

public enum Result {
    WIN, LOSE, DRAW;

    private static final int MAXIMUM_SUM_OF_CARD = 21;

    public static Result checkWinningResult(final int sumOfDealerCards,final int sumOfPlayerCards) {
        return dealerCompareToPlayer(sumOfDealerCards, sumOfPlayerCards);
    }

    private static Result dealerCompareToPlayer(final int sumOfStandardPlayerCards, final int sumOfOpponentPlayerCards) {
        if (sumOfStandardPlayerCards > MAXIMUM_SUM_OF_CARD && sumOfOpponentPlayerCards > MAXIMUM_SUM_OF_CARD) {
            return Result.DRAW;
        }
        if (sumOfStandardPlayerCards > MAXIMUM_SUM_OF_CARD) {
            return Result.LOSE;
        }
        if (sumOfOpponentPlayerCards > MAXIMUM_SUM_OF_CARD) {
            return Result.WIN;
        }
        return compareNumber(sumOfStandardPlayerCards, sumOfOpponentPlayerCards);
    }

    private static Result compareNumber(int sumOfDealerCards, int sumOfPlayerCards) {
        int resultNum = Integer.compare(sumOfDealerCards, sumOfPlayerCards);
        if (resultNum == 1) {
            return Result.WIN;
        }
        if (resultNum == 0) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }
}
