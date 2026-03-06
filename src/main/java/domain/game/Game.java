package domain.game;

import domain.player.Dealer;
import domain.player.Gamblers;
import java.util.List;

public class Game {
    private final Dealer dealer;
    private final Gamblers gamblers;

    public Game(List<String> names, int amount) {
        this.dealer = new Dealer("딜러", amount);
        this.gamblers = new Gamblers(names);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }


}

