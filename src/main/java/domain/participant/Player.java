package domain.participant;

import static domain.participant.Dealer.DEALER_NAME;

import constants.ErrorCode;
import domain.amount.BetAmount;
import exception.ReservedPlayerNameException;
import java.util.Objects;

public class Player extends Participant {

    private final BetAmount betAmount;

    Player(final Name name, final Hands hands, final BetAmount betAmount) {
        super(name, hands);
        this.betAmount = betAmount;
    }

    Player(final Name name, final Hands hands) {
        super(name, hands);
        validate(name);
        this.betAmount = new BetAmount(10);
    }

    public Player(final Name name, final BetAmount betAmount) {
        super(name, Hands.createEmptyHands());
        this.betAmount = betAmount;
    }

    private void validate(final Name name) {
        if (DEALER_NAME.equals(name)) {
            throw new ReservedPlayerNameException(ErrorCode.RESERVED_NAME);
        }
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }

    @Override
    public boolean canDeal() {
        return handsSum() < Hands.BLACK_JACK;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Player player)) {
            return false;
        }
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
