package blackjack.domain.cardGenerator;

import blackjack.domain.card.PlayingCard;
import java.util.Deque;

@FunctionalInterface
public interface CardGenerator {

    Deque<PlayingCard> generate();
}
