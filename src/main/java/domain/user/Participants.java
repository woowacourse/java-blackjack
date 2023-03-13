package domain.user;

import domain.game.Deck;

import java.util.List;

public class Participants {

    private static final String PLAYER_NOT_EXIST_EXCEPTION_MESSAGE = "해당 이름의 플레이어는 존재하지 않습니다.";

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = List.copyOf(players);
    }


    public void hitAll(final Deck deck) {
        dealer.hit(deck.serve());

        for (Player player : players) {
            player.hit(deck.serve());
        }
    }

    public void hitPlayer(final String name, final Deck deck) {
        Player player = findPlayer(name);
        player.hit(deck.serve());
    }

    public Player findPlayer(final String name) {
        return players.stream()
                .filter(player -> player.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_EXIST_EXCEPTION_MESSAGE));
    }

    public boolean hitDealer(final Deck deck) {
        if (dealer.canHit()) {
            dealer.hit(deck.serve());
            return true;
        }
        dealer.stay();
        return false;
    }

    public void stayPlayer(final String name) {
        findPlayer(name).stay();
    }

    public boolean isRunning(final String name) {
        return findPlayer(name).getState().isRunning();
    }

    public int dealerProfit() {
        return -players.stream()
                .map(this::profit)
                .reduce(0, Integer::sum);
    }

    public int profit(final Player player) {
        if (player.getState().isStay() && dealer.getState().isStay()) {
            return player.calculateStay(dealer);
        }
        return player.calculate();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
