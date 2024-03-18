package domain;

import domain.player.ActionAfterDealerHit;
import domain.player.ActionAfterPlayerHit;
import domain.player.Dealer;
import domain.player.DecisionOfPlayer;
import domain.player.Players;
import dto.GameResult;
import dto.ParticipantsResponse;
import java.util.List;

public class Blackjack {
    public static final int PERFECT_SCORE = 21;

    private final Players players;
    private final Dealer dealer;

    public Blackjack(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static void init(final Players players, final Dealer dealer) {
        dealer.init(dealer.draw(), dealer.draw());
        players.stream().forEach(player -> player.init(dealer.draw(), dealer.draw()));
    }

    public static Blackjack of(final List<String> names, final List<Integer> betAmounts) {
        final Dealer dealer = new Dealer();
        final Players players = Players.of(names, betAmounts);
        init(players, dealer);
        return new Blackjack(players, dealer);
    }

    public void playersHit(final DecisionOfPlayer decisionOfPlayer, final ActionAfterPlayerHit actionAfterPlayerHit) {
        players.stream().forEach(player -> player.automaticHit(dealer, decisionOfPlayer, actionAfterPlayerHit));
    }

    public void dealerHit(final ActionAfterDealerHit actionAfterDealerHit) {
        dealer.automaticHit(actionAfterDealerHit);
    }

    public GameResult toGameResult() {
        return GameResult.of(dealer, players);
    }

    public ParticipantsResponse toParticipantsResponse() {
        return ParticipantsResponse.of(dealer, players);
    }
}
