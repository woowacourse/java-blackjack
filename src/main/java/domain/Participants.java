package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final Dealer dealer;
    private final List<User> users;

    public Participants(List<User> users) {
        this(new Dealer(), users);
    }

    Participants(Dealer dealer, List<User> users) {
        this.dealer = dealer;
        this.users = users;
    }

    public static Participants of(List<String> names) {
        List<User> users = names.stream()
                .map(User::new)
                .collect(Collectors.toList());
        return new Participants(users);
    }

    User find(User user) {
        return users.stream()
                .filter(someUser -> someUser.equals(user))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("참가중인 플레이어가 아닙니다"));
    }

    void dealCardsFrom(Deck deck) {
        for (User user : users) {
            user.addCard(deck.draw());
        }
        dealer.addCard(deck.draw());
    }

    List<Result> getDealerResults() {
        List<Result> results = new ArrayList<>();

        for (User user : users) {
            results.add(dealer.competeWith(user));
        }
        return results;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(users);
        return players;
    }

    public List<User> getUsers() {
        return users;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
