package domain.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private final Dealer dealer;
    private final Gamblers gamblers;

    public Participants(List<String> names, int amount) {
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
