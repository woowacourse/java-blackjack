package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.card.Cards;
import domain.card.Deck;

public class Users {

	private static final int PLAYER_MIN_SIZE = 1;
	private static final int PLAYER_MAX_SIZE = 4;

	private final List<User> users;

	private Users(final List<User> users) {
		this.users = users;
	}

	public static Users from(final List<String> names, Deck deck) {
		validate(names);
		List<User> users = new ArrayList<>();
		for (String name : names) {
			users.add(new Player(name, new Cards()));
		}
		users.add(new Dealer(new Cards()));
		initTwoCards(users, deck);
		return new Users(users);
	}

	private static void initTwoCards(final List<User> users, Deck deck) {
		for(User user : users){
			user.hit(deck.pickCard());
			user.hit(deck.pickCard());
		}
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
}
