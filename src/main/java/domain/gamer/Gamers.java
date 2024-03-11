package domain.gamer;

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
        for (Player player : players.getPlayers()) {
            gamers.add(player);
        }
        return new Gamers(gamers);
    }

    public List<Player> findPlayers() {
        return gamers.stream()
                .filter(Gamer::isPlayer)
                .map(gamer -> (Player) gamer)
                .toList();
    }

    public Dealer findDealer() {
        return gamers.stream()
                .filter(Gamer::isDealer)
                .map(gamer -> (Dealer) gamer)
                .findFirst()
                .orElseThrow();
    }

    public List<Gamer> getGamers() {
        return gamers;
    }
}
