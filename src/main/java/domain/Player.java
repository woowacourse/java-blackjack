package domain;

public class Player extends Participant {
    private final PlayerName name;

    public Player(PlayerName name) {
        this.name = name;
    }

    public String getName() {
        return name.getName();
    }
}
