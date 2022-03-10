package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCards {
    private final List<PlayingCard> playingCards;

    public PlayingCards() {
        playingCards = new ArrayList<>();
    }

    public void addCard(PlayingCard playingCard) {
        playingCards.add(playingCard);
    }

    public int getResultWithPeekCard(final PlayingCard peekedCard) {
        return getResult() + peekedCard.getScore();
    }

    public List<PlayingCard> getPlayingCards() {
        return Collections.unmodifiableList(playingCards);
    }

    public int getResult() {
        return playingCards.stream()
            .mapToInt(playingCard -> playingCard.getDenomination().getScore())
            .sum();
    }


}
