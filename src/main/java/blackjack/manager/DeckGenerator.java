package blackjack.manager;

import blackjack.domain.Card;
import java.util.List;

public interface DeckGenerator {

    List<Card> generate();
}
