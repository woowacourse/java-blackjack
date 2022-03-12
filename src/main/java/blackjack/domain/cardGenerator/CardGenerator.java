package blackjack.domain.cardGenerator;

import blackjack.domain.card.PlayingCard;
import java.util.Stack;

public interface CardGenerator {
    Stack<PlayingCard> generate();
}
