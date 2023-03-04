package domain;

public class Dealer extends Player {

    private static final int ADD_CARD_MINIMUM_CONDITION = 17;
    private static final int BURST_CONDITION = 21;

    public Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public boolean isSumUnderStandard() {
        return sumOfPlayerCards() < ADD_CARD_MINIMUM_CONDITION;
    }

    public int checkWinningResult(final Player player) {
        int sumOfDealerCards = this.sumOfPlayerCards();
        int sumOfPlayerCards = player.sumOfPlayerCards();
        return compareDealerToPlayer(sumOfDealerCards, sumOfPlayerCards);
    }

    private int compareDealerToPlayer(final int sumOfDealerCards, final int sumOfPlayerCards) {
        if (sumOfDealerCards > BURST_CONDITION && sumOfPlayerCards > BURST_CONDITION) {
            return 0;
        }
        if (sumOfDealerCards > BURST_CONDITION) {
            return -1;
        }
        if (sumOfPlayerCards > BURST_CONDITION) {
            return 1;
        }

        return Integer.compare(sumOfDealerCards, sumOfPlayerCards);
    }
}
