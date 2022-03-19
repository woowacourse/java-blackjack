package blackjack.domain.card;

import java.util.Deque;

@FunctionalInterface
public interface CardGenerator {

    Deque<Card> generate();
}
