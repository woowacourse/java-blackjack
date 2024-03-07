package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gamers {
    private final List<Gamer> gamers;

    private Gamers(List<Gamer> gamers) {
        this.gamers = List.copyOf(gamers);
    }

    public static Gamers of(Players players, Dealer dealer){
        List<Gamer> gamers = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            gamers.add(player);
        }
        gamers.add(dealer);
        return new Gamers(gamers);
    }

    public List<Card> getHandAt(int index){
        return gamers.get(index).getHand();
    }

    public List<Gamer> getGamers() {
        return gamers;
    }
}
