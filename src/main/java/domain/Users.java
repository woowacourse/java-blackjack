package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Users {

    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 4;

    private final List<User> users;

    private Users(final List<User> users) {
        this.users = users;
    }

    public static Users from(final List<String> names) {
        validate(names);
        Dealer dealer = new Dealer();
        List<User> users = names.stream()
            .map(Player::new)
            .collect(Collectors.toList());
        users.add(dealer);
        return new Users(users);
    }

    private static void validate(final List<String> names) {
        validateDuplication(names);
        validateSize(names);
    }

    private static void validateDuplication(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateSize(final List<String> names) {
        if (names.size() < PLAYER_MIN_SIZE || names.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    public void hitCardByName(final String name, final Card card) {
        findByName(name).hit(card);
    }

    private Player findByName(final String name) {
        return getPlayers().stream()
            .filter(player -> player.isRightName(name))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public List<Player> getPlayers() {
        return users.stream()
            .filter(user -> user instanceof Player)
            .map(user -> (Player) user)
            .collect(Collectors.toUnmodifiableList());
    }

    public Dealer getDealer() {
        User dealer = users.stream()
            .filter(user -> user instanceof Dealer)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
        return (Dealer) dealer;
    }
}
