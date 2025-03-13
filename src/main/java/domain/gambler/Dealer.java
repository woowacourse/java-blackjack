package domain.gambler;

import domain.card.Card;
import domain.card.Cards;
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
