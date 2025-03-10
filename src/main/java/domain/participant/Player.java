package domain.participant;

import domain.result.BlackjackResult;

public class Player extends Participant {

    private final String name;

    private Player(final String name) {
        super();
        this.name = name;
    }

    public static Player of(final String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    public BlackjackResult getBlackjackResult(Dealer dealer) {
        return BlackjackResult.getPlayerResult(dealer, this);
    }
}
