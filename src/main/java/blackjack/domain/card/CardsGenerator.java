package blackjack.domain.card;

import java.util.List;

@FunctionalInterface
public interface CardsGenerator {

    List<Card> generate();
}
