package domain.player;

import domain.card.Card;
import dto.CardResponse;
import dto.PlayerResponse;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

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
        final Profit profit = new Profit(state.earningRate(), betAmount.getValue());
        if (dealer.compareHandsWith(this) == Result.WIN) {
            return profit.lose();
        }
        if (dealer.compareHandsWith(this) == Result.LOSE) {
            return profit.win();
        }
        return profit.tie();
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

    public void hit(final Dealer dealer, final Function<String, Boolean> tryHit,
                    final BiConsumer<String, List<CardResponse>> printStatus) {
        final Boolean apply = tryHit.apply(name.getValue());
        if (!apply || state.isFinished()) {
            return;
        }
        add(dealer.draw());
        printStatus.accept(name.getValue(), getHands().stream().map(Card::toCardResponse).toList());
        hit(dealer, tryHit, printStatus);
    }
}
