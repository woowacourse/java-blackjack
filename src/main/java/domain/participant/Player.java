package domain.participant;

public class Player extends Participant {

    private final PlayerName playerName;

    public Player(String name) {
        super();
        this.playerName = new PlayerName(name);
    }

    public String getName() {
        return playerName.name();
    }
}
