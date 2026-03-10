package domain.participant;

public class Player extends Participant{

    private Player(PlayerName name) {
        super(name);
    }

    public static Player from(PlayerName name) {
        return new Player(name);
    }
}
