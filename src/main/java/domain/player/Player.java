package domain.player;

import domain.card.Card;

import java.util.List;

public class Player extends User {
    public Player(String name, List<Card> cards) {
        super(name, cards);
    }
}
