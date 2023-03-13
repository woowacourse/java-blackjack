package domain.game;

import domain.user.Dealer;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {

    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, List<Player> players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }

    public void hitAll() {
        dealer.hit(deck.serve());

        for (Player player : players) {
            player.hit(deck.serve());
        }
    }

    public void hitPlayer(String name) {
        Player player = findPlayer(name);
        player.hit(deck.serve());
    }

    public boolean hitDealer() {
        if (dealer.canHit()) {
            dealer.hit(deck.serve());
            return true;
        }
        dealer.stay();
        return false;
    }

    public void stayPlayer(String name) {
        findPlayer(name).stay();
    }

    public boolean isRunning(String name) {
        return findPlayer(name).getState().isRunning();
    }

    public int dealerProfit() {
        return -players.stream()
                .map(this::profit)
                .reduce(0, Integer::sum);
    }

    public int profit(Player player) {
        if (player.getState().isStay() && dealer.getState().isStay()) {
            return player.calculateStay(dealer);
        }
        return player.calculate();
    }

    public Player findPlayer(String name) {
         return players.stream()
                .filter(player -> player.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 플레이어는 존재하지 않습니다."));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
