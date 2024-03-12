package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.player.bet.BetAmount;
import blackjack.domain.rule.state.HitState;
import blackjack.domain.rule.state.StandState;
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

    public static Player of(final PlayerName name, final Dealer dealer) {
        return new Player(new PlayerBetAmount(name), HitState.start(dealer.drawCard(), dealer.drawCard()));
    }

    public PlayerName getName() {
        return playerBetAmount.getName();
    }

    public void setBetAmount(final BetAmount betAmount) {
        playerBetAmount.setBetAmount(betAmount);
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
    }

    public void hit(final Card card, final BiConsumer<PlayerName, Hands> printHands) {
        state = state.draw(card);
        printHands.accept(getName(), getHands());
    }

    public void stand(final BiConsumer<PlayerName, Hands> printHands) {
        state = state.stand();
        if (state.countHit() == 0) {
            printHands.accept(getName(), getHands());
        }
    }

    public boolean isContinueHit(final Predicate<PlayerName> isHitInput) {
        return state.isHit() && isHitInput.test(getName());
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

    public BetAmount getBetAmount() {
        return playerBetAmount.getBetAmount();
    }

    public State getState() {
        return new StandState(state.getHands());
    }
}
