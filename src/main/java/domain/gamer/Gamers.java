package domain.gamer;

import java.util.List;
import java.util.stream.Stream;

import domain.card.Deck;

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

	public Dealer getDealer() {
		return dealer;
	}

	public Stream<Player> stream() {
		return players.stream();
	}
}
