package domain.card.shuffler;

import domain.card.Card;
import java.util.Stack;

public interface DeckShuffler {
    Stack<Card> shuffleDeck(Stack<Card> deck);
}
