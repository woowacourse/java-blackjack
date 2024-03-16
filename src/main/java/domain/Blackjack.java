package domain;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import dto.GameResult;
import dto.ParticipantsResponse;
import dto.PlayerResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Blackjack {
    private final Players players;
    private final Dealer dealer;

    public Blackjack(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static void init(final Players players, final Dealer dealer) {
        dealer.init(dealer.draw(), dealer.draw());
        players.stream().forEach(r -> r.init(dealer.draw(), dealer.draw()));
    }

//    public static Blackjack of(final Players players) {
//        final Dealer dealer1 = new Dealer();

//        init(players, dealer1);
//        return new Blackjack(players, dealer1);
    //    }
    public static Blackjack fromNames(final List<String> names) {
        final Dealer dealer1 = new Dealer();
        final Players players1 = Players.fromNames(names);
        init(players1, dealer1);
        return new Blackjack(players1, dealer1);

    }

    public static Blackjack of(final List<String> names, final List<Integer> betAmounts) {
        final Dealer dealer1 = new Dealer();
        final Players players1 = Players.of(names, betAmounts);
        init(players1, dealer1);
        return new Blackjack(players1, dealer1);
    }

    public boolean canPlayerHit(final String name) {
        return getPlayers().stream()
                .filter(player -> player.getName().equals(name))
                .anyMatch(Player::canHit);
    }

    public void playerHit(final String name) {
        final Player player = players.findPlayerByName(name);
        player.add(dealer.draw());
    }

    public void playerStand(final String name) {
        final Player player = players.findPlayerByName(name);
        player.stand();
    }

    public void dealerHit() {
        final Card nextCard = dealer.draw();
        dealer.add(nextCard);
    }

    public void dealerHit(final Runnable runnable) {
        dealer.hit(runnable);
    }

    public void dealerStand() {
        dealer.stand();
    }

    public boolean canDealerHit() {
        return dealer.canHit();
    }

    public GameResult toGameResult() {
        final Map<String, Double> playerResult = players.getValue()
                .stream()
                .collect(Collectors.toMap(Player::getName, player -> player.calculateProfit(dealer),
                        (a, b) -> a,
                        LinkedHashMap::new));
        final double sum = players.stream().map(player -> player.calculateProfit(dealer)).mapToDouble(r -> -r).sum();
        return new GameResult(sum, playerResult);
    }

    public ParticipantsResponse toParticipantsResponse() {
        return new ParticipantsResponse(dealer.toDealerResponse(),
                players.stream().map(Player::toPlayerResponse).toList());
    }

    public PlayerResponse toPlayerResponse(final String name) {
        return players.findPlayerByName(name).toPlayerResponse();
    }

    public Players getPlayers() {
        return players;
    }

    public Player getPlayer(final String name) {
        return players.findPlayerByName(name);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
