package blackjack.domain.card;

import java.util.Stack;

public interface CardShuffleMachine {

    void shuffle(Stack<PlayingCard> playingCards);
}
