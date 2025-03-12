package model.participant;

public class Player extends Participant {
    private final String name;

    public Player(String name) {
        super();
        this.name = name;
    }

    public boolean checkBlackjack() {
        return getParticipantHand().checkBlackjack();
    }

    public String getName() {
        return name;
    }
}
