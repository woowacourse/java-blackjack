package domain;

public class Dealer extends Player {
    public Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public boolean isSumUnderStandard() {
        return sumOfPlayerCards() < 17;
    }

    public int checkWinningResult(final Player player) {
        int sumOfDealerCards = this.sumOfPlayerCards();
        int sumOfPlayerCards = player.sumOfPlayerCards();

        if (sumOfDealerCards > 21 && sumOfPlayerCards > 21) {
            return 0;
        }
        if (sumOfDealerCards > 21) {
            return -1;
        }
        if (sumOfPlayerCards > 21) {
            return 1;
        }

        return Integer.compare(sumOfDealerCards, sumOfPlayerCards);
    }
}
