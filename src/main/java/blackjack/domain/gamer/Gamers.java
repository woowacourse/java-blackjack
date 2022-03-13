package blackjack.domain.gamer;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;

public class Gamers {

	private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";
	private static final String NOT_EXIST_PLAYER_ERROR = "플레이어가 존재하지 않습니다.";

	private final Dealer dealer;
	private final List<Player> players;

	public Gamers(List<String> names) {
		validateDuplicationNames(names);
		this.dealer = new Dealer();
		this.players = names.stream()
			.map(Player::new)
			.collect(Collectors.toList());
	}

	private void validateDuplicationNames(List<String> names) {
		int count = (int) names.stream()
			.distinct()
			.count();
		if (count != names.size()) {
			throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
		}
	}

	public void giveCardToAllGamers(Supplier<Card> deck) {
		dealer.addCard(deck.get());
		for (Player player : players) {
			player.addCard(deck.get());
		}
	}

	public void giveCardToDealer(Supplier<Card> deck) {
		dealer.addCard(deck.get());
	}

	public void giveCardToPlayer(String name, Supplier<Card> deck) {
		Player player = findPlayerByName(name);
		player.addCard(deck.get());
	}

	public boolean checkDealerDrawPossible() {
		return !dealer.isOverThan(BlackJackGame.ADDITIONAL_DISTRIBUTE_STANDARD);
	}

	public Player findPlayerByName(String name) {
		return players.stream()
			.filter(player -> player.isSameName(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER_ERROR));
	}

	public boolean checkPlayerDrawPossible(String name) {
		return findPlayerByName(name).isOverThan(BlackJackGame.MAX_CARD_VALUE);
	}

	public List<String> findPlayerNames() {
		return players.stream()
			.map(Player::getName)
			.map(Name::getValue)
			.collect(Collectors.toList());
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
