package blackjack.domain.player.dealercardstate;

import blackjack.domain.card.Card;
import java.util.List;

public class AllCardsOpenStateDealer implements DealerCardOpenState {
    @Override
    public List<Card> cards(List<Card> cards) {
        return cards;
    }

    @Override
    public DealerCardOpenState stateChange() {
        return this;
    }
}
