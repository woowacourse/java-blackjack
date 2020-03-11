package domain.Gamer;

import domain.Card.PlayingCards;

public class Player extends Gamer {
    private String name;

    Player(String name, PlayingCards playingCards) {
        super(playingCards);
        this.name = name;
    }
}


