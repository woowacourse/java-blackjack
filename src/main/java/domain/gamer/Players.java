package domain.gamer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class Players {
	private static final int MAX_PLAYER = 5;
	private static final int EMPTY = 0;

	private List<Player> players;

	public Players(String[] playersName) {
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
		if ((playerNames == null) || (playerNames.length == EMPTY)) {
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

	public void draw(Deck deck) {
		players.forEach(player -> player.draw(deck.deal()));
	}

	public void findResult(int dealerScore) {
		players.forEach(player -> player.findResult(dealerScore));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int countPlayerLose() {
		return (int)players.stream()
			.filter(player -> player.getPlayerGameResult().isLose())
			.count();
	}

	public int countPlayerWin() {
		return (int)players.stream()
			.filter(player -> player.getPlayerGameResult().isWin())
			.count();
	}

	public int countPlayerDraw() {
		return (int)players.stream()
			.filter(player -> player.getPlayerGameResult().isDraw())
			.count();
	}

}
