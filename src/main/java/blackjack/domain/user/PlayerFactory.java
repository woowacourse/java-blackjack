package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.HashSet;
import java.util.List;

import blackjack.domain.exceptions.InvalidPlayerException;

public class PlayerFactory {
	private static final int MAX_PLAYER_NUMBER = 7;

	public static List<Player> create(List<String> playerNames) {
		validate(playerNames);
		return playerNames.stream()
			.map(Player::new)
			.collect(toList());
	}

	private static void validate(List<String> playerNames) {
		validateDuplication(playerNames);
		validateSize(playerNames);
	}

	private static void validateDuplication(List<String> playerNames) {
		long distinctPlayersSize = new HashSet<>(playerNames).size();

		if (playerNames.size() != distinctPlayersSize) {
			throw new InvalidPlayerException(InvalidPlayerException.DUPLICATE_PLAYER);
		}

		if (playerNames.contains(Dealer.NAME)) {
			throw new InvalidPlayerException(InvalidPlayerException.DUPLICATE_DEALER);
		}
	}

	private static void validateSize(List<String> playerNames) {
		if (playerNames.size() > MAX_PLAYER_NUMBER) {
			throw new InvalidPlayerException(InvalidPlayerException.SIZE);
		}
	}

}
