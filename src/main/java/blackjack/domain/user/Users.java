package blackjack.domain.user;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final Dealer dealer;
    private final List<Player> players;

    public Users(List<Player> players) {
        this(new Dealer(), players);
    }

    public Users(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = Collections.unmodifiableList(players);
    }

    public List<User> getUsers() {
        List<User> users = new LinkedList<>(players);
        users.add(dealer);
        return users;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
