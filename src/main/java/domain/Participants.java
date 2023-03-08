package domain;

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

    public boolean has(Player player) {
        return isExistingDealer(player) || isExistingUser(player);
    }

    public void dealFrom(Deck deck) {
        for (User user : users) {
            user.drawFrom(deck);
        }
        dealer.drawFrom(deck);
    }

    private boolean isExistingUser(Player player) {
        return users.stream()
                .anyMatch(someUser -> someUser.equals(player));
    }

    private boolean isExistingDealer(Player player) {
        return dealer.equals(player);
    }

    public List<User> getUsers() {
        return users;
    }

    public Dealer getDealer() {
        return dealer;
    }
}