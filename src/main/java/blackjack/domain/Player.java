package blackjack.domain;

import blackjack.domain.card.Cards;
import java.util.ArrayList;

public class Player extends Gamer {

    public Player(String name) {
        super(name, new Cards(new ArrayList<>()));
    }

    @Override
    public void hit() {
        draw();
    }
}
