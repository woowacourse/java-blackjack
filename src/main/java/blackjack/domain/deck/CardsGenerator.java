package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.Stack;

public interface CardsGenerator {
    Stack<Card> generate();
}
