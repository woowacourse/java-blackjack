package deck;

import java.util.Stack;

import card.Card;

public interface CardsGenerator {
    Stack<Card> generate();
}
