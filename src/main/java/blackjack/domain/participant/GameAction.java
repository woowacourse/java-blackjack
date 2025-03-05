package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public interface GameAction {

    List<Card> showInitialCards();
}
