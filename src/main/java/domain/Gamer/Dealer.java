package domain.Gamer;

import domain.Card.PlayingCards;

public class Dealer extends Gamer {
    private final PlayingCards playingCards;

    Dealer(PlayingCards playingCards) {
        this.playingCards = playingCards;
    }
}
