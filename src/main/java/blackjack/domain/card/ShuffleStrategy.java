package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class ShuffleStrategy implements ProcessStrategy {
    @Override
    public void shuffle(Stack<Card> cards) {
        Collections.shuffle(cards);
    }
}