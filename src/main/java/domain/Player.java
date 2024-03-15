package domain;

import domain.state.Hit;
import domain.state.Ready;
import domain.state.State;

public class Player extends Participant {
    private final BetAmount betAmount;

    private Player(final Name name, final State state, final BetAmount betAmount) {
        super(name, state);
        this.betAmount = betAmount;
        validateName(name);
    }

    public Player(final Name name, final BetAmount betAmount) {
        this(name, new Ready(new Hand()), betAmount);
    }

    private void validateName(final Name name) {
        if (name.isDealerName()) {
            throw new IllegalArgumentException("플레이어는 [딜러] 이름을 사용할 수 없습니다.");
        }
    }

    @Override
    public boolean canHit() {
        return state instanceof Hit;
    }
}
