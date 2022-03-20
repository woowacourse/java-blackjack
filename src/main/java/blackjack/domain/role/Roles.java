package blackjack.domain.role;

import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roles {

	private static final String CAN_NOT_FIND_ERROR = "해당 플레이어를 찾을 수 없습니다.";

	private final Role dealer;
	private final List<Role> players;

	public Roles(final Role dealer, final List<Role> players) {
		this.dealer = dealer;
		this.players = players;
	}

	public List<Role> ready(Deck deck) {
		List<Role> roles = new ArrayList<>();
		dealer.ready(deck);
		roles.add(dealer);

		for (Role player : players) {
			player.ready(deck);
			roles.add(player);
		}
		return roles;
	}

	public boolean isDealerBlackjack() {
		return dealer.isFinished();
	}

	public PlayerTurns getPlayerTurn() {
		return new PlayerTurns(players);
	}

	public Role drawPlayer(final PlayerDrawChoice answer, final String name, Deck deck) {
		Role player = getPlayerByName(name);
		if (answer == PlayerDrawChoice.NO) {
			player.stay();
			return player;
		}
		player.draw(deck);
		return player;
	}

	private Role getPlayerByName(final String name) {
		return players.stream()
				.filter(player -> player.getName().equals(name))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(CAN_NOT_FIND_ERROR));
	}

	public Role drawDealer(Deck deck) {
		if (!dealer.isFinished()) {
			dealer.draw(deck);
		}
		if (!dealer.isFinished()) {
			dealer.stay();
		}
		return dealer;
	}

	public Map<String, Money> getPlayersProfit() {
		Map<String, Money> playerProfit = new HashMap<>();
		for (Role player : players) {
			Money money = player.settle(dealer);
			playerProfit.put(player.getName(), money);
		}
		return playerProfit;
	}

	public Role getDealer() {
		return dealer;
	}

	public List<Role> getPlayers() {
		return new ArrayList<>(players);
	}
}