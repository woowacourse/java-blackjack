package domain.gamer;

import domain.card.Deck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gamers {
	private List<Player> players;
	private Dealer dealer;

	public Gamers(List<Player> players, Dealer dealer) {
		this.players = players;
		this.dealer = dealer;
	}

	public void initCard(Deck deck) {
		players.forEach(player -> player.addCard(deck.popCard(2)));
		dealer.addCard(deck.popCard(2));
	}

	public List<Boolean> gameResult() {
		return players.stream()
			.map(Gamer::calculateWithAce)
			.map(player -> player < dealer.calculateWithAce())
			.collect(Collectors.toList());
	}

	public Dealer getDealer() {
		return dealer;
	}

	public Stream<Player> stream() {
		return players.stream();
	}
}
