package blackjack.domain.card;

import java.util.List;

public class PlayingCardFixMachine implements CardShuffleMachine {

    @Override
    public PlayingCard assignCard(List<PlayingCard> playingCards) {
        shuffle(playingCards);
        return playingCards.remove(0);
    }

    @Override
    public void shuffle(List<PlayingCard> playingCards) {
        return;
    }
}
