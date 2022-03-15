package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class PlayingCardShuffleMachine implements CardShuffleMachine {

    @Override
    public void shuffle(List<PlayingCard> playingCards) {
        Collections.shuffle(playingCards);
    }
}
