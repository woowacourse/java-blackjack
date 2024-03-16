package domain.player;

import domain.card.Card;
import dto.PlayerResponse;
import java.util.Objects;

public final class Player extends Participant {
    private final Name name;
    private final BetAmount betAmount;

    public Player(final Name name) {
        this.name = name;
        betAmount = BetAmount.LOWEST;
    }

    public Player(final Name name, final BetAmount betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public double calculateProfit(final Dealer dealer) {
        final Profit profit = new Profit(getState().earningRate(), betAmount.getValue());
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
        final boolean decisionToStop = !decisionOfPlayer.apply(name.getValue());
        if (decisionToStop || getState().isFinished()) {
            finish();
            return;
        }

        hit(dealer);
        actionAfterPlayerHit.accept(name.getValue(), getHands().stream().map(Card::toCardResponse).toList());

        automaticHit(dealer, decisionOfPlayer, actionAfterPlayerHit);
    }

    private void hit(final Dealer dealer) {
        add(dealer.draw());
    }

    public PlayerResponse toPlayerResponse() {
        return new PlayerResponse(getName(), getHands().stream().map(Card::toCardResponse).toList(), getScore());
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
