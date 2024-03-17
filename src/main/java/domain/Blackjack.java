package domain;

import domain.player.ActionAfterDealerHit;
import domain.player.ActionAfterPlayerHit;
import domain.player.Dealer;
import domain.player.DecisionOfPlayer;
import domain.player.Player;
import domain.player.Players;
import dto.GameResult;
import dto.ParticipantsResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        final Map<String, Double> playerResult = players.getValue()
                .stream()
                .collect(Collectors.toMap(Player::getName, player -> player.calculateProfit(dealer), (a, b) -> a,
                        LinkedHashMap::new));

//        final double dealerProfit = -1.0 * players.getSum();

        return new GameResult(dealerProfit, playerResult);
    }

    public ParticipantsResponse toParticipantsResponse() {
        return new ParticipantsResponse(dealer.toDealerResponse(),
                players.stream().map(Player::toPlayerResponse).toList());
    }
}
