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
    private static final String DEALER_NAME = "딜러";

    private final List<User> users;

    private Users(final List<User> users) {
        this.users = users;
    }

    public static Users from(final List<String> names) {
        validate(names);
        List<User> users = new ArrayList<>();
        for(String name : names){
            users.add(new Player(name));
        }
        users.add(new Dealer(DEALER_NAME));
        return new Users(users);
    }

    private static void validate(final List<String> names) {
        validateDuplication(names);
        validateSize(names);
    }

    private static void validateDuplication(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private static void validateSize(final List<String> names) {
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

	public Dealer getDealer() {
		User dealer = users.stream()
			.filter(user -> user instanceof Dealer)
			.findAny()
			.orElseThrow(IllegalArgumentException::new);
		return (Dealer)dealer;
	}

	public List<Player> getPlayers() {
		return users.stream()
			.filter(user -> user instanceof Player)
			.map(user -> (Player)user)
			.collect(Collectors.toUnmodifiableList());
	}

	public List<User> getUsers() {
		return users;
	}

    public List<Player> getHittablePlayers() {
        List<Player> players = getPlayers();
        return players.stream()
            .filter(Player::isHittable)
            .collect(Collectors.toUnmodifiableList());
    }
}
