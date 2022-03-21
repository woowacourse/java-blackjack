package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardShuffleMachine implements CardMachine {

    @Override
    public void shuffleCards(Stack<Card> deck) {
        Collections.shuffle(deck);
    }
}
