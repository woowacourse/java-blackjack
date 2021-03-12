package blackjack.domain.player.dealercardstate;

import blackjack.domain.card.Card;
import java.util.List;

public interface DealerCardOpenState {
    List<Card> cards(List<Card> cards);

    DealerCardOpenState stateChange();
}
