package domain;

public class Player extends Participant {
    private static final int burstThreshold = 21;

    private Player(Name name) {
        super(name);
    }

    public static Player from(String name) {
        return new Player(new Name(name));
    }

    public boolean canReceiveCard() {
        return cards.canReceiveCard(burstThreshold);
    }
}
