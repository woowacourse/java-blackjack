package blackjack.domain.CardGenerator;

import blackjack.domain.PlayingCard;
import java.util.Stack;

public interface CardGenerator {
    Stack<PlayingCard> generate();
}
