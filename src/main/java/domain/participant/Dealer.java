package domain.participant;

import domain.BlackJackWinningResult;
import domain.card.Card;
import domain.card.CardBox;
import domain.card.Cards;

public class Dealer extends Participant {

    private static final int ADD_CARD_MINIMUM_CONDITION = 17;
    private static final int BURST_CONDITION = 21;
    private static final String DEALER_DEFAULT_NAME = "딜러";

    private final CardBox cardBox;

    public Dealer(final CardBox cardBox, final Cards cards) {
        super(new Name(DEALER_DEFAULT_NAME), cards);
        this.cardBox = cardBox;
    }

    public Card draw() {
        return cardBox.get();
    }

    public boolean isSumUnderStandard() {
        return sumOfCards() < ADD_CARD_MINIMUM_CONDITION;
    }

    public BlackJackWinningResult checkWinningResult(final Player player) {
        int sumOfDealerCards = this.sumOfCards();
        int sumOfPlayerCards = player.sumOfCards();
        return compareDealerToPlayer(sumOfDealerCards, sumOfPlayerCards);
    }

    private BlackJackWinningResult compareDealerToPlayer(final int sumOfDealerCards, final int sumOfPlayerCards) {
        if (sumOfPlayerCards > BURST_CONDITION) {
            return BlackJackWinningResult.from(1);
        }
        if (sumOfDealerCards > BURST_CONDITION) {
            return BlackJackWinningResult.from(-1);
        }

        return BlackJackWinningResult.from(Integer.compare(sumOfDealerCards, sumOfPlayerCards));
    }
}
