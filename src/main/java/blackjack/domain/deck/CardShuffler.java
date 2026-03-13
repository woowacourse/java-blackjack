package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.List;

public interface CardShuffler {

    void shuffle(List<Card> cards);
}
