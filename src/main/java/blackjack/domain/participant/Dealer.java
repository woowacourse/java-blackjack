package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_DISTRIBUTE_CARD_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    @Override
    public List<Card> openInitialCards() {
        return List.of(super.handCards.getCards().getFirst());
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() <= DEALER_DISTRIBUTE_CARD_THRESHOLD;
    }
}
