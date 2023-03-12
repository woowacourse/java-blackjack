package domain.participant;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;

public class Participants {

    private final Dealer dealer;
    private final List<User> users;

    public Participants(List<User> users) {
        this(new Dealer(), users);
    }

    public Participants(Dealer dealer, List<User> users) {
        this.dealer = dealer;
        this.users = users;
    }

    public static Participants of(List<String> names) {
        List<User> users = names.stream()
                .map(User::new)
                .collect(Collectors.toList());
        return new Participants(users);
    }

    public void dealFrom(Deck deck) {
        for (User user : users) {
            user.drawFrom(deck);
        }
        dealer.drawFrom(deck);
    }

    public Player find(Player player) {
        if (isExistingDealer(player) || isExistingUser(player)) {
            return player;
        }
        throw new IllegalArgumentException("참가중인 플레이어가 아닙니다");
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