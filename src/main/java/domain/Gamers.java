package domain;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

import java.util.ArrayList;
import java.util.List;

public class Gamers {
    private final List<Gamer> gamers;

    private Gamers(final List<Gamer> gamers) {
        this.gamers = List.copyOf(gamers);
    }

    public static Gamers of(final Players players, final Dealer dealer) {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(players.getPlayers());
        return new Gamers(gamers);
    }

    public List<Player> findPlayers() {
        List<Player> players = new ArrayList<>();
        gamers.stream()
                .filter(gamer -> gamer instanceof Player)
                .forEach(gamer -> players.add((Player) gamer));
        return players;
    }

    public Dealer findDealer() {
        return (Dealer) gamers.stream()
                .filter(gamer -> gamer instanceof Dealer)
                .findFirst()
                .orElseThrow();
    }

    public List<Gamer> getGamers() {
        return gamers;
    }
}
