package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import java.util.List;

public interface CardDeckGenerator {

    List<Card> generate();
}
