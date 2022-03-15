package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import java.util.List;

@FunctionalInterface
public interface CardsGenerator {

    List<Card> generate();
}
