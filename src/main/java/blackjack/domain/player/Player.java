package blackjack.domain.player;

import blackjack.domain.HandGenerator;
import blackjack.domain.Name;

public class Player extends Participant {
    public Player(Name name, HandGenerator handGenerator) {
        super(name, handGenerator);
    }
}
