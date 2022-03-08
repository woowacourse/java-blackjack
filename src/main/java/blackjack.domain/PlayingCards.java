package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayingCards {
    private final List<PlayingCard> playingCards = new ArrayList<>();

    public void addCard(PlayingCard playingCard) {
        playingCards.add(playingCard);
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }

    public int getResult() {
        return playingCards.stream()
            .mapToInt(playingCard -> playingCard.getDenomination().getScore())
            .sum();
    }
}
