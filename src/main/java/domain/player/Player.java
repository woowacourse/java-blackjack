package domain.player;

import domain.card.Card;
import domain.player.attribute.CardStatus;
import domain.player.attribute.Name;
import java.util.List;

public class Player extends Participant{

    // player마다 다른 전략(확장)
    // Hit or Stand 결정

    public Player(String name) {
        super(name);
    }
}
