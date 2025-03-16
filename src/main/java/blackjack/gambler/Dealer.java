package blackjack.gambler;

import blackjack.card.Card;
import blackjack.card.Cards;
import java.util.List;

public class Dealer extends Gambler {

    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Cards cards;

    public Dealer(Cards cards) {
        super(cards);
        this.cards = cards;
    }

    @Override
    public List<Card> openInitialCards() {
        return cards.openDealerInitialCards();
    }

    public boolean isSumUnderThreshold() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }
}
