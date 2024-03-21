package domain;

import domain.player.ActionAfterDealerHit;
import domain.player.ActionAfterPlayerHit;
import domain.player.Dealer;
import domain.player.DecisionOfPlayer;
import domain.player.Players;
import java.util.List;

public class Blackjack {
    public static final int PERFECT_SCORE = 21;

    private final Players players;
    private final Dealer dealer;

    private Blackjack(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    private static void init(final Players players, final Dealer dealer) {
        dealer.init(dealer.draw(), dealer.draw());
        players.init(dealer.draw(), dealer.draw());
    }

    public static Blackjack startWithInitialization(final List<String> names, final List<Integer> betAmounts) {
        final Dealer dealer = new Dealer();
        final Players players = Players.of(names, betAmounts);
        init(players, dealer);
        return new Blackjack(players, dealer);
    }

    public void playersHit(final DecisionOfPlayer decisionOfPlayer, final ActionAfterPlayerHit actionAfterPlayerHit) {
        players.automaticHit(dealer, decisionOfPlayer, actionAfterPlayerHit);
    }

    public void dealerHit(final ActionAfterDealerHit actionAfterDealerHit) {
        dealer.automaticHit(actionAfterDealerHit);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
