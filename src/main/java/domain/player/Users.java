package domain.player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public Dealer getDealer() {
        return (Dealer) users.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 포함되어 있지 않습니다."));
    }

    public List<Player> getPlayer() {
        return Collections.unmodifiableList(users.stream()
                .filter(player -> player instanceof Player)
                .map(player -> (Player) player)
                .collect(Collectors.toList()));
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
