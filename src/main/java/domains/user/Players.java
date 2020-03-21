package domains.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import domains.card.Deck;
import domains.user.money.BettingMoney;
import domains.user.name.PlayerName;
import domains.user.name.PlayerNames;

public class Players implements Iterable<Player> {
	private List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public Players(String inputPlayerNames, Deck deck) {
		this.players = new ArrayList<>();
		PlayerNames playerNames = new PlayerNames(inputPlayerNames);
		for (PlayerName name : playerNames) {
			players.add(new Player(name, deck));
		}
	}

	public Map<Player, BettingMoney> bet(Function<Player, String> inputBettingMoney) {
		Map<Player, BettingMoney> playerBettingMoney = new HashMap<>();
		for (Player player : players) {
			playerBettingMoney.put(player, new BettingMoney(inputBettingMoney.apply(player)));
		}
		return playerBettingMoney;
	}

	public void hitOrStay(Deck deck, Function<Player, String> inputYesOrNo, Consumer<Player> printHands) {
		for (Player player : players) {
			while (player.needMoreCard(deck, inputYesOrNo.apply(player))) {
				printHands.accept(player);
			}
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}
}
