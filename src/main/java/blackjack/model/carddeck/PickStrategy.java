package blackjack.model.carddeck;

import blackjack.model.card.Card;
import java.util.List;

public interface PickStrategy {

    Card pick(List<Card> cards);
}
