package domain;

import domain.card.Card;
import java.util.Stack;

public interface DecksGenerator {

    Stack<Card> generate();
}
