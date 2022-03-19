package blackjack.domain.state.stateparticipant;

import blackjack.domain.participant.Name;

public class StatePlayer extends StateParticipant {

    private final Name name;

    public StatePlayer(Name name) {
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }
}
