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
	private List<Player> players;

	public Players(Names names, List<Money> moneys) {
		players = new ArrayList<>();
		for (int i = 0; i < moneys.size(); i++) {
			players.add(new Player(names.getNames().get(i), moneys.get(i)));
		}
	}

	public void draw(Deck deck) {
		players.forEach(player -> player.draw(deck.deal()));
	}

	public List<Player> getPlayers() {
		return players;
	}
}
