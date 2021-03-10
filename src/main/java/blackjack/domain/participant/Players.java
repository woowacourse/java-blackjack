package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import blackjack.domain.card.Deck;
import blackjack.domain.game.WinnerFlag;

public class Players {
	private final List<Player> players;
	private final Gamer dealer;

	public Players(List<String> value, Dealer dealer) {
		this.players = createPlayers(value);
		this.dealer = dealer;
	}

	private List<Player> createPlayers(List<String> value) {
		List<Player> splitPlayers = new ArrayList<>();
		for (String name : value) {
			splitPlayers.add(new Player(name));
		}
		return splitPlayers;
	}

	public void giveCardToPlayers(Deck deck) {
		dealer.receiveCard(deck.dealCard());
		for (Player player : players) {
			player.receiveCard(deck.dealCard());
		}
	}

	public void makeState() {
		for (Player player : players) {
			player.makeState();
		}
	}

	public Map<WinnerFlag, Integer> calculateTotalWinnings(Dealer dealer) {
		Map<WinnerFlag, Integer> winnerCount = new EnumMap<>(WinnerFlag.class);
		for (Player player : players) {
			winnerCount.put(player.calculateResult(dealer), winnerCount.getOrDefault(player.getResult(), 0) + 1);
		}
		return winnerCount;
	}

	public List<Player> toList() {
		return players;
	}

	public String getDealerName() {
		return dealer.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Players players1 = (Players)o;
		return Objects.equals(players, players1.players) && Objects.equals(dealer, players1.dealer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(players, dealer);
	}
}
