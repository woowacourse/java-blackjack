package model;

import java.util.List;
import java.util.stream.Collectors;

public class Users {

    private final List<User> users;

    public Users(final List<String> playersName) {
        this.users = createPlayers(playersName);
    }

    private List<User> createPlayers(final List<String> playersName) {
        final List<User> players = playersName.stream()
                .map(playerName -> new Player(playerName, Hand.create()))
                .collect(Collectors.toList());

        players.add(0, Dealer.getInstance());

        return players;
    }

    public List<User> getUsers() {
        return users;
    }

}
