package blackjack.domain.player;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.bet.BetLeverage;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.rule.state.PlayerHitState;
import blackjack.domain.rule.state.State;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Player {

    private final PlayerName playerName;
    private final BetAmount betAmount;
    private State state;

    private Player(final PlayerName playerName, final BetAmount betAmount, final State state) {
        this.playerName = playerName;
        this.betAmount = betAmount;
        this.state = state;
    }

    public static Player of(final PlayerName name, final BetAmount amount, final Dealer dealer) {
        return new Player(name, amount, PlayerHitState.start(dealer.drawCard(), dealer.drawCard()));
    }

    public void hit(
            final Dealer dealer,
            final Predicate<PlayerName> userWantToHit,
            final BiConsumer<PlayerName, Hands> callAfter
    ) {
        while (state.isHit()) {
            runHitOrStandByUser(dealer, userWantToHit);
            callAfter.accept(getPlayerName(), getHands());
        }
    }

    private void runHitOrStandByUser(
            final Dealer dealer,
            final Predicate<PlayerName> userWantToHit
    ) {
        if (userWantToHit.test(getPlayerName())) {
            final Card card = dealer.drawCard();
            state = state.draw(card);
            return;
        }
        state = state.stand();
    }

    public BetRevenue calculateBetRevenue(final Dealer dealer) {
        final BetLeverage betLeverage = dealer.calculateBetLeverage(state);

        return betLeverage.applyLeverage(betAmount);
    }

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isHit() {
        return state.isHit();
    }

    public Hands getHands() {
        return state.getHands();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

}
