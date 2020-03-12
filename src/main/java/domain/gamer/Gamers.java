package domain.gamer;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Deck;
import utils.InputUtils;

public class Gamers {
	private List<Player> players;
	private Dealer dealer;

	public Gamers(String players, Dealer dealer) {
		this.players = InputUtils.splitAsDelimiter(players)
			.stream()
			.map(Player::new)
			.collect(toList());
		this.dealer = dealer;
	}

	public void initCard(Deck deck) {
		players.forEach(player -> player.addCard(deck.popCard(2)));
		dealer.addCard(deck.popCard(2));
	}

	public Map<String, WinOrLose> generateGameResults() {
		return players.stream()
			.collect(Collectors.toMap(Gamer::getName,
				player -> player.isWinOrLose(dealer.calculateWithAce())));
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
