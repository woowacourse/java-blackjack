package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.rule.state.PlayerHitState;
import blackjack.domain.rule.state.State;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Player {

    private final PlayerBetAmount playerBetAmount;
    private State state;

    private Player(final PlayerBetAmount playerBetAmount, final State state) {
        this.playerBetAmount = playerBetAmount;
        this.state = state;
    }

    public static Player of(final PlayerBetAmount playerBetAmount, final Dealer dealer) {
        return new Player(playerBetAmount, PlayerHitState.start(dealer.drawCard(), dealer.drawCard()));
    }

    public void hit(
            final Dealer dealer,
            final Predicate<PlayerName> isHitInput,
            final BiConsumer<PlayerName, Hands> printHands
    ) {
        while (isContinueHit(isHitInput)) {
            final Card card = dealer.drawCard();
            state = state.draw(card);
            printHands.accept(getName(), getHands());
        }

        stand(printHands);
    }

    private boolean isContinueHit(final Predicate<PlayerName> isHitInput) {
        return state.isHit() && isHitInput.test(getName());
    }

    private void stand(final BiConsumer<PlayerName, Hands> printHands) {
        state = state.stand();
        if (state.countHit() == 0) {
            printHands.accept(getName(), getHands());
        }
    }

    public BetLeverage calculateBetLeverage(final Dealer dealer) {
        return dealer.calculateBetLeverage(state);
    }

    public boolean isBurst() {
        return state.isBurst();
    }

    public boolean isHit() {
        return state.isHit();
    }

    public Hands getHands() {
        return state.getHands();
    }

    public PlayerName getName() {
        return playerBetAmount.name();
    }

    public BetAmount getBetAmount() {
        return playerBetAmount.betAmount();
    }
}
