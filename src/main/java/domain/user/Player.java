package domain.user;

import domain.card.PlayingCards;

public class Player extends User {
    public Player(PlayingCards playingCards, String name) {
        super(playingCards, name);
    }

    public boolean wantToHit(String willForMoreCard) {
        return willForMoreCard.equals("y");
    }
}


