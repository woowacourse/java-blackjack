package blackjack.domain.cardGenerator;

import blackjack.domain.card.PlayingCard;
import java.util.Stack;

@FunctionalInterface
public interface CardGenerator {

    Stack<PlayingCard> generate();
}
