package player;

import cardGame.GameParticipant;
import java.util.ArrayList;

public class Player extends GameParticipant {

    private final Name name;

    public Player(Name name) {
        super(new ArrayList<>());
        this.name = name;
    }
}
