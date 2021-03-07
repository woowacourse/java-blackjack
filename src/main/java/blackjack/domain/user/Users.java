package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users = new ArrayList<>();

    public Users(List<String> playerNames) {
        users.add(new Dealer());
        isDuplicatePlayers(playerNames);
        playerNames.stream()
                .forEach(name -> users.add(new Player(name)));
    }

    public Dealer getDealer() {
        return (Dealer) users.get(0);
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    private void isDuplicatePlayers(List<String> players) {
        if (players.stream().distinct().count() != players.size()) {
            throw new IllegalArgumentException("플레이어가 중복됩니다!");
        }
    }
}
