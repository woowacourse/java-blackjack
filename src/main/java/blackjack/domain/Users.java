package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {

    private final List<User> users;

    public Users(List<String> playerNames, Deck deck) {
        List<User> users = playerNames.stream()
                .map(name -> new Player(name, initCardGroup(deck)))
                .collect(Collectors.toList());
        users.add(new Dealer(initCardGroup(deck)));
        this.users = List.copyOf(users);
    }

    private CardGroup initCardGroup(Deck deck) {
        return new CardGroup(deck.draw(), deck.draw());
    }

    public Map<String, List<Card>> getStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getStatus));
    }

    public Map<String, List<Card>> getInitialStatus() {
        return users.stream()
                .collect(Collectors.toUnmodifiableMap(User::getName, User::getInitialStatus));
    }
}
