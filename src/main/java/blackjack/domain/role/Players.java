package blackjack.domain.role;

import blackjack.domain.game.Betting;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Players {

	private final List<Role> players;

	public Players(final List<Role> players) {
		this.players = players;
	}

	public Role getPlayerByName(final String name) {
		return players.stream()
				.filter(player -> player.getName().equals(name))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public PlayerTurns getPlayerTurn() {
		return new PlayerTurns(players);
	}

	public List<Role> ready(final Deck deck) {
		for (Role player : players) {
			player.ready(deck);
		}
		return players;
	}

	public List<Role> getPlayers() {
		return new ArrayList<>(players);
	}

	public Map<String, Money> competeToDealer(final Role dealer, final Betting betting) {
		Map<String, Money> betMonies = new HashMap<>();
		for (Role player : players) {
			Money money = player.settle(dealer, betting.getBettingByName(player.getName()));
			betMonies.put(player.getName(), money);
		}
		return betMonies;
	}
}
