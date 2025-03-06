package domain.gamer;

import domain.card.CardGroup;

public class Player extends Gamer{
    private final String name;

    public Player(String name, CardGroup cardGroup) {
        super(cardGroup);
        this.name = name;
    }

    public Player(Player player){
        super(player.cardGroup);
        this.name = player.name;
    }
}
