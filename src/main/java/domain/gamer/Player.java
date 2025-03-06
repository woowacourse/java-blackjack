package domain.gamer;

import domain.card.CardGenerator;
import domain.card.CardGroup;

public class Player extends Gamer {
    private final String name;

    public Player(String name, CardGroup cardGroup, CardGenerator cardGenerator) {
        super(cardGroup, cardGenerator);
        this.name = name;
    }

    public Player(Player player) {
        super(player.cardGroup, player.cardGenerator);
        this.name = player.name;
    }

    public String getName() {
        return name;
    }
}
