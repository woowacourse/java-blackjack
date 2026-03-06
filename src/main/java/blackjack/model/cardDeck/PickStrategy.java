package blackjack.model.cardDeck;

import blackjack.model.card.Card;
import java.util.List;

public interface PickStrategy {

    Card pick(List<Card> cards);
}
