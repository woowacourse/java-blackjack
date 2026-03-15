package blackjack.model.carddeck;

import blackjack.model.card.Card;
import java.util.List;

public interface PickStrategy {

    Card pick(final List<Card> cards);
}
