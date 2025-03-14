package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import java.util.List;

public interface DeckGenerator {

    List<Card> makeDeck();
}
