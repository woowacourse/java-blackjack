package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

/**
 *    블랙잭 게임 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class BlackjackGame {
	private static final int MAX_PLAYER = 5;
	private static final int EMPTY = 0;
	private static final int INITIAL_DRAW_SIZE = 2;

	private List<Player> players;
	private Dealer dealer;
	private Deck deck;

	public BlackjackGame(String[] playersName) {
		validate(playersName);
		this.players = Arrays.stream(playersName)
			.map(Player::new)
			.collect(Collectors.toList());
		this.dealer = new Dealer();
		this.deck = new Deck();
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

	public void initialDraw() {
		for (int i = 0; i < INITIAL_DRAW_SIZE; i++) {
			dealer.hit(deck.deal());
			players.forEach(player -> player.hit(deck.deal()));
		}
	}

	public boolean isDealerBlackjack() {
		return dealer.isBlackjack();
	}

	public boolean isAllPlayersBust() {
		return players.stream()
			.allMatch(Player::isBust);
	}

	public void draw(Gamer gamer) {
		gamer.hit(deck.deal());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Dealer getDealer() {
		return dealer;
	}
}
