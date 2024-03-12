package blackjack.domain.player;

import blackjack.domain.rule.state.State;

public class Player {

    private final PlayerName name;
    private State state;

    public Player(final PlayerName name, final State state) {
        this.name = name;
        this.state = state;
    }


}
