package domain;

import java.util.List;

public class Participants {

    private List<Player> players;
    private Dealer dealer;

    public Participants(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Dealer getDealer() {
        return dealer;
    }
}