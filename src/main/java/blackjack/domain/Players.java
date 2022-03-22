package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class Players {

	private static final String NOT_EXIST_PLAYER_ERROR = "플레이어가 존재하지 않습니다.";

	private final List<Player> value;

	public Players(Map<String, Integer> players) {
		this.value = players.entrySet().stream()
			.map(entry -> new Player(entry.getKey(), entry.getValue()))
			.collect(toList());
	}

	public void handOutFirst(Deck deck) {
		for (int i = 0; i < Cards.BLACKJACK_SIZE; i++) {
			handOutToAllPlayers(deck);
		}
	}

	private void handOutToAllPlayers(Deck deck) {
		for (Player player : value) {
			player.addCard(deck.draw());
		}
	}

	public void handOut(String name, Deck deck) {
		Player player = findPlayerByName(name);
		player.addCard(deck.draw());
	}

	public int calculatePlayerEarning(String name, Dealer dealer) {
		return findPlayerByName(name).match(dealer);
	}

	private Player findPlayerByName(String name) {
		return value.stream()
			.filter(player -> player.isSameName(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER_ERROR));
	}

	public List<Card> findCards(String name) {
		return findPlayerByName(name).getCards();
	}

	public boolean checkBust(String name) {
		return !findPlayerByName(name).isDrawable();
	}

	public List<String> findNames() {
		return value.stream()
			.map(Player::getName)
			.collect(toList());
	}

	public List<Player> getValue() {
		return new ArrayList<>(value);
	}
}
