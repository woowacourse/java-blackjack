package domain;

public class Dealer extends Player {

    private static final int MINIMUM_SUM_OF_DEALERS_CARD = 17;
    private static final int MAXINUM_SUM_OF_CARD = 21;

    public Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public boolean isSumUnderStandard() {
        return sumOfPlayerCards() < MINIMUM_SUM_OF_DEALERS_CARD;
    }

    public Result checkWinningResult(final Player player) {
        int sumOfDealerCards = this.sumOfPlayerCards();
        int sumOfPlayerCards = player.sumOfPlayerCards();
        return dealerCompareToPlayer(sumOfDealerCards, sumOfPlayerCards);
    }

    private static Result dealerCompareToPlayer(final int sumOfDealerCards, final int sumOfPlayerCards) {
        if (sumOfDealerCards > MAXINUM_SUM_OF_CARD && sumOfPlayerCards > MAXINUM_SUM_OF_CARD) {
            return Result.DRAW;
        }
        if (sumOfDealerCards > MAXINUM_SUM_OF_CARD) {
            return Result.LOSE;
        }
        if (sumOfPlayerCards > MAXINUM_SUM_OF_CARD) {
            return Result.WIN;
        }
        return compareNumber(sumOfDealerCards, sumOfPlayerCards);
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
