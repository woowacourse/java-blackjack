package domain.participant;

import domain.card.Hand;
import domain.participant.state.Ready;
import domain.participant.state.State;

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
        return state.isHit();
    }

    public void stand() {
        state = state.stand();
    }

    public int betAmount() {
        return betAmount.toInt();
    }

    public int profit(final Dealer dealer) {
        return (int) (betAmount() * state.profitRate(dealer.state));
    }
}
