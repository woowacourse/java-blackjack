package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final Deck deck;
    private final Dealer dealer;
    private final List<User> users;

    public Game(Dealer dealer, List<User> users, Deck deck) {
        this.dealer = dealer;
        this.users = users;
        this.deck = deck;
    }

    public void dealTwice() {
        for (int i = 0; i < 2; i++) {
            for (User user : users) {
                user.drawFrom(deck);
            }
            dealer.drawFrom(deck);
        }
    }

    public void dealTo(Player player) {
        player.drawFrom(deck);
    }

    public Result getResultOf(User user) {
        return user.competeWith(dealer);
    }

    public List<Result> getDealerResults() {
        return users.stream()
                .map(dealer::competeWith)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(users);
        return players;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public Dealer getDealer() {
        return dealer;
    }
}