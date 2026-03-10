package domain.participant;

import domain.strategy.PlayerDrawStrategy;

public class Player extends Participant {
    public Player(String name) {
        super(name, new PlayerDrawStrategy());
    }
}
