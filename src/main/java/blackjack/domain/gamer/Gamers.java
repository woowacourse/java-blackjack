package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Gamers {

	private static final String NOT_EXIST_PLAYER_ERROR = "플레이어가 존재하지 않습니다.";

	private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;

	private final Dealer dealer;
	private final List<Player> players;

	public Gamers(List<Player> players) {
		this.dealer = new Dealer();
		this.players = new ArrayList<>(players);
	}

	public void giveCardToAllGamers(Deck deck) {
		dealer.addCard(deck.draw());
		for (Player player : players) {
			player.addCard(deck.draw());
		}
	}

	public void giveCardToDealer(Deck deck) {
		dealer.addCard(deck.draw());
	}

	public void giveCardToPlayer(String name, Deck deck) {
		Player player = findPlayerByName(name);
		player.addCard(deck.draw());
	}

	public boolean checkDealerDrawPossible() {
		return !dealer.isOverThan(ADDITIONAL_DISTRIBUTE_STANDARD);
	}

	public List<Card> findCardsOfPlayer(String name) {
		return findPlayerByName(name).getCards();
	}

	public Player findPlayerByName(String name) {
		return players.stream()
			.filter(player -> player.isSameName(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER_ERROR));
	}

	public boolean checkPlayerDrawPossible(String name) {
		return findPlayerByName(name).isBust();
	}

	public List<String> findPlayerNames() {
		return players.stream()
			.map(Player::getName)
			.collect(Collectors.toList());
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
