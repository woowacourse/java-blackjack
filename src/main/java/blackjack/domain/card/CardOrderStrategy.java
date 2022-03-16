package blackjack.domain.card;

import java.util.List;

@FunctionalInterface
public interface CardOrderStrategy {

    List<Card> generate();
}
