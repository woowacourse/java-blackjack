package blackjack.domain;

import java.util.List;

public class PlayingCards {
    private final List<PlayingCard> playingCards;

    public PlayingCards(final List<PlayingCard> playingCards) {
        this.playingCards = List.copyOf(playingCards);
    }

    public void addCard(PlayingCard playingCard) {
        playingCards.add(playingCard);
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }
}
