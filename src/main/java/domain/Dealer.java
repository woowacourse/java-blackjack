package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int ADD_CARD_MINIMUM_CONDITION = 17;
    private static final int BURST_CONDITION = 21;
    private static final String DEALER_DEFAULT_NAME = "딜러";
    private static final int FIRST_CARD_INDEX = 0;

    private final CardBox cardBox;

    public Dealer(final CardBox cardBox, final Cards cardss) {
        super(new Name(DEALER_DEFAULT_NAME), cardss);
        this.cardBox = cardBox;
    }

    public Card draw() {
        return cardBox.get();
    }

    public boolean isSumUnderStandard() {
        return sumOfCards() < ADD_CARD_MINIMUM_CONDITION;
    }

    public int checkWinningResult(final Player player) {
        int sumOfDealerCards = this.sumOfCards();
        int sumOfPlayerCards = player.sumOfCards();
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

    @Override
    public List<String> printInitCards() {
        List<String> dealerCardsToString = cards.cardsToString();
        List<String> cardsToString = new ArrayList<>();
        cardsToString.add(dealerCardsToString.get(FIRST_CARD_INDEX));
        return cardsToString;
    }
}
