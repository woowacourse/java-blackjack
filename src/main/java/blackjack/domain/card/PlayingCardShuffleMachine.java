package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class PlayingCardShuffleMachine implements CardShuffleMachine {

    private static final int POP = 0;

    @Override
    public PlayingCard assignCard(List<PlayingCard> playingCards) {
        shuffle(playingCards);
        return playingCards.remove(POP);
    }

    @Override
    public void shuffle(List<PlayingCard> playingCards) {
        Collections.shuffle(playingCards);
    }
}
