package domain.gamer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;

/**
 *    Player 리스트를 가진 일급 콜렉션
 *
 *    @author ParkDooWon
 */
public class Players {
	private static final int MAX_PLAYER = 5;
	private static final int MIN_PLAYER = 1;

	private final List<Player> players;

	public Players(final String[] playersName) {
		validate(playersName);
		players = Arrays.stream(playersName)
			.map(Player::new)
			.collect(Collectors.toList());
	}

	private void validate(String[] playersName) {
		validateNullAndEmpty(playersName);
		validateDuplicatedName(playersName);
		validateHeadcount(playersName);
	}

	private void validateNullAndEmpty(String[] playerNames) {
		if ((playerNames == null) || (playerNames.length < MIN_PLAYER)) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateDuplicatedName(String[] playersName) {
		int distinctSize = (int)Arrays.stream(playersName)
			.distinct().count();

		if (distinctSize != playersName.length) {
			throw new IllegalArgumentException("중복된 이름이 있습니다.");
		}
	}

	private void validateHeadcount(String[] playersName) {
		if (playersName.length > MAX_PLAYER) {
			throw new IllegalArgumentException("인원수 초과입니다.");
		}
	}

	public void hitAll(Deck deck) {
		players.forEach(player -> player.hit(deck.deal()));
	}

	public boolean isAllPlayersBust() {
		return players.stream()
			.allMatch(Player::isBust);
	}

	public List<Player> getPlayers() {
		return this.players;
	}
}
