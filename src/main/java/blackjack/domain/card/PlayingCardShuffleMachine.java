package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class PlayingCardShuffleMachine implements CardShuffleMachine {

    @Override
    public void shuffle(Stack<PlayingCard> playingCards) {
        Collections.shuffle(playingCards);
    }
}
