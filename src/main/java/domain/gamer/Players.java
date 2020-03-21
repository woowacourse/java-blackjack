package domain.gamer;

import java.util.List;

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

	public Players(final List<Player> players) {
		validate(players);
		this.players = players;
	}

	private void validate(List<Player> players) {
		validateNullAndEmpty(players);
		validateDuplicatedName(players);
		validateHeadcount(players);
	}

	private void validateNullAndEmpty(List<Player> players) {
		if ((players == null) || (players.size() < MIN_PLAYER)) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateDuplicatedName(List<Player> players) {
		int distinctSize = (int)players.stream()
			.distinct().count();

		if (distinctSize != players.size()) {
			throw new IllegalArgumentException("중복된 이름이 있습니다.");
		}
	}

	private void validateHeadcount(List<Player> players) {
		if (players.size() > MAX_PLAYER) {
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
