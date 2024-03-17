package blackjack.domain.card;

import java.util.List;

@FunctionalInterface
public interface CardFactory {

    List<Card> generate();
}
