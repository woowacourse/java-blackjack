package domain;

public class Dealer {

    private static final int ADD_CARD_MINIMUM_CONDITION = 17;
    private static final int BURST_CONDITION = 21;
    private static final String DEALER_DEFAULT_NAME = "딜러";

    private final Player player;
    private final CardBox cardBox;

    public Dealer(final CardBox cardBox, final Cards cardss) {
        this.player = new Player(new Name(DEALER_DEFAULT_NAME), cardss);
        this.cardBox = cardBox;
    }

    public Card draw() {
        return cardBox.get();
    }

    public boolean isSumUnderStandard() {
        return player.sumOfPlayerCards() < ADD_CARD_MINIMUM_CONDITION;
    }

    public int checkWinningResult(final Player player) {
        int sumOfDealerCards = this.player.sumOfPlayerCards();
        int sumOfPlayerCards = player.sumOfPlayerCards();
        return compareDealerToPlayer(sumOfDealerCards, sumOfPlayerCards);
    }

    private int compareDealerToPlayer(final int sumOfDealerCards, final int sumOfPlayerCards) {
        if (sumOfPlayerCards > BURST_CONDITION) {
            return 1;
        }
        if (sumOfDealerCards > BURST_CONDITION) {
            return -1;
        }

        return Integer.compare(sumOfDealerCards, sumOfPlayerCards);
    }

    public boolean addCard(final Card card) {
        return player.addCard(card);
    }

    public String getName() {
        return player.getName();
    }

    public Cards getCards() {
        return player.getCards();
    }

    public int sumOfCards() {
        return player.sumOfPlayerCards();
    }
}
