package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users;

    public Users(List<User> users) {
        this.users = new ArrayList<>(users);
    }

    public static Users of(List<String> playerNames) {
        Dealer dealer = new Dealer();
        isDuplicatePlayers(playerNames, dealer.getName());
        List<User> users = new ArrayList<>(Arrays.asList(dealer));
        playerNames.stream().forEach(name -> users.add(new Player(name)));
        return new Users(users);
    }

    public Dealer getDealer() {
        return (Dealer) users.stream()
                .filter(user -> user instanceof Dealer)
                .findAny()
                .get();
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    private static void isDuplicatePlayers(List<String> players, String dealer) {
        if (players.stream().distinct().count() != players.size() || players.contains(dealer)) {
            throw new IllegalArgumentException("유저 이름이 중복됩니다!");
        }
    }
}
