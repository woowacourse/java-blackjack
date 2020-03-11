package domain.gamer;

import domain.card.PlayingCards;

public class Player extends Gamer {
    private String name;

    Player(String name, PlayingCards playingCards) {
        super(playingCards);
        this.name = name;
    }
}


