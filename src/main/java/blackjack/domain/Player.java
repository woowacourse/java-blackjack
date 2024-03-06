package blackjack.domain;

import java.util.ArrayList;

public class Player extends Participant {
    private final PlayerName playerName;

    private Player(final PlayerName playerName, final Hands hands) {
        super(hands);
        this.playerName = playerName;
    }

    public static Player from(final String name) {
        return new Player(new PlayerName(name), new Hands(new ArrayList<>()));
    }

    public PlayerName getName() {
        return playerName;
    }
}
