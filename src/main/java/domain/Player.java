package domain;

public class Player extends Participant {
    private static final int burstThreshold = 21;

    public Player(Name name) {
        super(name);
    }

    public boolean canReceiveCard() {
        return cards.canReceiveCard(burstThreshold);
    }
}
