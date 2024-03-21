package domain.player;

import domain.card.Card;
import domain.state.Started;
import dto.CardResponse;
import java.util.Objects;

public final class Player extends Participant {
    private final Name name;
    private final BetAmount betAmount;

    public Player(final Name name, final BetAmount betAmount, final Card first, final Card second) {
        this.name = name;
        this.betAmount = betAmount;
        state = Started.ofTwoCard(first, second);
    }

    public double calculateProfit(final Dealer dealer) {
        final Profit profit = new Profit(getState().earningRate(), betAmount.value());
        if (compareHandsWith(dealer) == Result.WIN) {
            return profit.win();
        }
        if (compareHandsWith(dealer) == Result.LOSE) {
            return profit.lose();
        }
        return profit.tie();
    }

    private Result compareHandsWith(final Dealer dealer) {
        return Result.reverse(dealer.compareHandsWith(this));
    }

    public void automaticHit(
            final Dealer dealer,
            final DecisionOfPlayer decisionOfPlayer,
            final ActionAfterPlayerHit actionAfterPlayerHit
    ) {
        final boolean decisionToStop = !decisionOfPlayer.apply(name.value());
        if (decisionToStop || getState().isFinished()) {
            standIfRunning();
            return;
        }

        hit(dealer);
        actionAfterPlayerHit.accept(name.value(), getHands().stream().map(CardResponse::of).toList());

        automaticHit(dealer, decisionOfPlayer, actionAfterPlayerHit);
    }

    private void hit(final Dealer dealer) {
        add(dealer.draw());
    }

    public String getName() {
        return name.value();
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
