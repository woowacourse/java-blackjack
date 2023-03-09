package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Users {

    private static final int PLAYER_MIN_SIZE = 1;
    private static final int PLAYER_MAX_SIZE = 4;

    private final List<Player> players;
    private final Dealer dealer;

    private Users(final List<Player> players, final Dealer dealer) {
        this.players = List.copyOf(players);
        this.dealer = dealer;
    }

    public static Users from(final Map<String, Integer> playerNameToBettingAmount) {
        validate(playerNameToBettingAmount.keySet());
        Dealer dealer = new Dealer();
        List<Player> players = new ArrayList<>();
        for (Entry<String, Integer> player : playerNameToBettingAmount.entrySet()) {
            players.add(new Player(player.getKey(), player.getValue()));
        }
        return new Users(players, dealer);
    }

    private static void validate(final Set<String> names) {
        validateSize(names);
    }

    private static void validateSize(final Set<String> names) {
        if (names.size() < PLAYER_MIN_SIZE || names.size() > PLAYER_MAX_SIZE) {
            throw new IllegalArgumentException(
                String.format("플레이어 수는 %d명 이상, %d명 이하여야 합니다.", PLAYER_MIN_SIZE, PLAYER_MAX_SIZE));
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

    public boolean isDealerHittable() {
        return getDealer().isHittable();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(players);
        users.add(dealer);
        return users;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toUnmodifiableList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getHittablePlayers() {
        return players.stream()
            .filter(Player::isHittable)
            .collect(Collectors.toUnmodifiableList());
    }
}
