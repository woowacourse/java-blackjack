package blackjack.domain.participant;

import blackjack.domain.PlayStatus;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public void stay() {
        playStatus = PlayStatus.STAY;
    }

    public Name getName() {
        return name;
    }
}
