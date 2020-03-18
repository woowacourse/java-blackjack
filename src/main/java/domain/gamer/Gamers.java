package domain.gamer;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import domain.card.Deck;
import utils.InputUtils;

public class Gamers {
	private static final int INIT_CARD_SIZE = 2;

	private List<Player> players;
	private Dealer dealer;

	public Gamers(List<Player> players, Dealer dealer) {
		this.players = players;
		this.dealer = dealer;
	}

	public void initCard(Deck deck) {
		players.forEach(player -> player.addCard(deck.popCard(INIT_CARD_SIZE)));
		dealer.addCard(deck.popCard(INIT_CARD_SIZE));
	}

	public GameResult generateGameResults() {
		return players.stream()
			.collect(collectingAndThen(toMap(player -> player,
				player -> player.findMatchResult(dealer),
				(a, b) -> a,
				LinkedHashMap::new), GameResult::new));
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}