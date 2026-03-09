package blackjack.model;

import blackjack.util.PlayerParser;
import java.util.ArrayList;
import java.util.List;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

    public Users(String playerNames) {
        this.players = PlayerParser.parse(playerNames);
        this.dealer = new Dealer();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(List.copyOf(players));
        users.add(dealer);
        return users;
    }
}
