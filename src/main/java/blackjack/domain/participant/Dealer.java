package blackjack.domain.participant;

import blackjack.domain.HandGenerator;
import blackjack.domain.Name;
import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int INITIAL_OPENED_CARD_COUNT = 1;

    public Dealer(HandGenerator handGenerator) {
        super(new Name(DEALER_NAME), handGenerator);
    }

    @Override
    public List<Card> getInitialOpenedCards() {
        return getCardsByCount(INITIAL_OPENED_CARD_COUNT);
    }
}
