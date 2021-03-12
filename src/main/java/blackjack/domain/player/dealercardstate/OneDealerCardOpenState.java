package blackjack.domain.player.dealercardstate;

import blackjack.domain.card.Card;
import java.util.List;

public class OneDealerCardOpenState implements DealerCardOpenState {
    public static final int ONE_CARD_SIZE = 1;

    @Override
    public List<Card> cards(List<Card> cards) {
        return cards.subList(0, ONE_CARD_SIZE);
    }

    @Override
    public DealerCardOpenState stateChange() {
        return new AllCardsOpenStateDealer();
    }
}
