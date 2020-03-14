package domain.player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final List<User> men;

    public Users(List<User> men) {
        this.men = men;
    }

    public Dealer getDealer() {
        return (Dealer) men.stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public List<Player> getUsers() {
        return Collections.unmodifiableList(men.stream()
                .filter(player -> player instanceof Player)
                .map(player -> (Player) player)
                .collect(Collectors.toList()));
    }
}
