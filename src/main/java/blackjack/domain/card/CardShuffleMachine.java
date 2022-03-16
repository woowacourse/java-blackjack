package blackjack.domain.card;

import java.util.List;

public interface CardShuffleMachine {

    void shuffle(List<PlayingCard> playingCards);
}
