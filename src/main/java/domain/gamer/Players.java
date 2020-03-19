package domain.gamer;

import java.util.ArrayList;
import java.util.List;

import domain.card.Deck;
import domain.money.Money;

/**
 *   Player 리스트를 가진 일급 콜렉션
 *
 *   @author ParkDooWon
 */
public class Players {
	private static final int MAX_PLAYER = 5;
	private static final int EMPTY = 0;

	private List<Player> players;

	public Players(List<Name> playersName, List<Money> moneys) {
		validate(playersName);
		players = new ArrayList<>();
		for (int i = 0; i < playersName.size(); i++) {
			players.add(new Player(playersName.get(i), moneys.get(i)));
		}
	}

	private void validate(List<Name> playersName) {
		validateNullAndEmpty(playersName);
		validateDuplicatedName(playersName);
		validateHeadcount(playersName);
	}

	private void validateNullAndEmpty(List<Name> playerNames) {
		if ((playerNames == null) || (playerNames.size() == EMPTY)) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateDuplicatedName(List<Name> playersName) {
		int distinctSize = (int)playersName.stream()
			.distinct().count();

		if (distinctSize != playersName.size()) {
			throw new IllegalArgumentException("중복된 이름이 있습니다.");
		}
	}

	private void validateHeadcount(List<Name> playersName) {
		if (playersName.size() > MAX_PLAYER) {
			throw new IllegalArgumentException("인원수 초과입니다.");
		}
	}

	public void draw(Deck deck) {
		players.forEach(player -> player.draw(deck.deal()));
	}

	public List<Player> getPlayers() {
		return players;
	}
}
