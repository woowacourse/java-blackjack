package blackjack.domain.card;

import java.util.List;

@FunctionalInterface
public interface CardsFactory {

    List<Card> generate();
}
