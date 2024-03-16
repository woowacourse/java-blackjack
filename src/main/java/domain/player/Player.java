package domain.player;

import domain.card.Card;
import dto.PlayerResponse;
import java.util.Objects;

public class Player extends Participant {
    private final Name name;
    private BetAmount betAmount;

    public Player(final Name name) {
        this.name = name;
    }

    public Player(final Name name, final BetAmount betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public PlayerResponse toPlayerResponse() {
        return new PlayerResponse(getName(), getHands().stream().map(Card::toCardResponse).toList(),
                getScore());
    }

    public double calculateProfit(final Dealer dealer) {
        final double result = state.earningRate() * betAmount.getValue();
        if (dealer.compareHandsWith(this) == Result.WIN) {
            return -1 * result;
        }
        if (dealer.compareHandsWith(this) == Result.LOSE) {
            return result;
        }
        return 0;
    }

    @Override
    public boolean canHit() {
        return state.isRunning();
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
