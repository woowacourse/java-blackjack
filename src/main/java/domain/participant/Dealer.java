package domain.participant;

import domain.BlackJackWinningResult;
import domain.money.Money;
import domain.card.Card;
import domain.card.CardBox;
import domain.card.Cards;

public class Dealer extends Participant {

    private static final int ADD_CARD_MINIMUM_CONDITION = 17;
    private static final int BURST_CONDITION = 21;
    private static final int DEFAULT_DEALER_MONEY = 0;

    private final CardBox cardBox;

    public Dealer(final CardBox cardBox, final Cards cards) {
        super(new Money(DEFAULT_DEALER_MONEY), cards);
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

    public void changeMoney(final int sumOfPlayersProfit) {
        this.money = new Money(sumOfPlayersProfit);
    }
}
