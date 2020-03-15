package domain.gamer;

import domain.card.PlayingCards;

public class Player extends Gamer {
    public Player(PlayingCards playingCards, String name) {
        super(playingCards, name);
    }

    @Override
    public boolean isHittable() {
        return isNotBust();
    }
}
