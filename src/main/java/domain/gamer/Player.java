package domain.gamer;

import domain.card.PlayingCards;

public class Player extends Gamer {
    public Player(PlayingCards playingCards, String name) {
        super(playingCards, name);
    }

    public boolean wantToHit(String willForMoreCard) {
        return willForMoreCard.equals("y");
    }
}


