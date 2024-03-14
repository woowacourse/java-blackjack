package domain.blackjack;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import domain.player.Players;
import dto.GameResult;
import dto.ParticipantsResponse;
import dto.PlayerResponse;
import dto.PlayerResult;
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

    public static Blackjack startGameWithInitialization(final Players players) {
        final Blackjack blackjack = new Blackjack(players, new Dealer());
        blackjack.init();
        return blackjack;
    }

    public static Blackjack fromPlayerNamesWithInitialization(final List<String> names) {
        return startGameWithInitialization(Players.fromNames(names));
    }

    public void init() {
        players.getValue().forEach(this::dealInitialCards);
        dealInitialCards(dealer);
    }


    public boolean canPlayerHit(final String name) {
        return getPlayers().stream()
                .filter(player -> player.getName().equals(name))
                .anyMatch(Player::canHit);
    }

    public void playerHit(final String name) {
        final Player player = players.findPlayerByName(name);
        player.hit(dealer.draw());
    }

    private void dealInitialCards(final Participant player) {
        player.hit(dealer.draw());
        player.hit(dealer.draw());
    }

    public boolean canDealerHit() {
        return false;
//        return dealer.canHit();
    }

    public void dealerHit() {
        final Card card = dealer.draw();
        dealer.hit(card);
    }

    public GameResult toGameResult() {
        final Map<String, PlayerResult> playerResult = players.getValue()
                .stream()
                .collect(Collectors.toMap(Player::getName, player -> player.obtainResultBy(dealer),
                        (a, b) -> a,
                        LinkedHashMap::new));
        final Map<PlayerResult, Integer> dealerResult = dealer.wrapUp(players);
        return new GameResult(dealerResult, playerResult);
    }

    public ParticipantsResponse toParticipantsResponse() {
//        return new ParticipantsResponse(dealer.toDealerResponse(),
//                players.stream().map(Player::toPlayerResponse).toList());
        return null;

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
