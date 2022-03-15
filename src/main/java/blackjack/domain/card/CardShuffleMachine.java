package blackjack.domain.card;

import java.util.List;

public interface CardShuffleMachine {

    PlayingCard assignCard(List<PlayingCard> playingCards);

    void shuffle(List<PlayingCard> playingCards);
}
