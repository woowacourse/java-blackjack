package domain.game;

import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import java.util.List;

public class Table {
    private final Dealer dealer;
    private final Players players;

    private Table(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Table of(Dealer dealer, Players players) {
        return new Table(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player findPlayer(String name) {
        return players.findByName(name);
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }

    public boolean areAllPlayersBust() {
        return players.areAllBust();
    }
}