package blackjack.domain.card;

import java.util.List;

@FunctionalInterface
public interface CardGeneratingStrategy {

    List<Card> generate();
}
