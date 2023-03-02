package domain.card.shuffler;

import domain.card.Card;
import java.util.Stack;

public interface CardsShuffler {

    public Stack<Card> shuffleCards(Stack<Card> cards);
}
