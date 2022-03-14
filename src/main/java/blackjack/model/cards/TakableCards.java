package blackjack.model.cards;

import blackjack.model.card.Card;
import java.util.Collection;

public interface TakableCards extends Cards {

    void take(Card card);
}
