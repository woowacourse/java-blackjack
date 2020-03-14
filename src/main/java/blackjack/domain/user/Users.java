package blackjack.domain.user;

import blackjack.domain.result.Result;

import java.util.*;
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

    public Map<Player, Result> calculateAllResults() {
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        players.forEach(player -> totalResult.put(player, Result.of(dealer, player)));
        return totalResult;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(players);
        users.add(0, dealer);
        return users;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
