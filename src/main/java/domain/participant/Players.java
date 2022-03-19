package domain.participant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.EarningRate;

public class Players {
	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public List<String> showNames() {
		return new ArrayList<>(players.stream().map(Player::showName).collect(Collectors.toList()));
	}

	public void addCard(int idx, Card card) {
		players.get(idx).addCard(card);
	}

	public boolean checkBust(int idx) {
		return players.get(idx).isBust();
	}

	public boolean checkBlackJack(int idx) {
		return players.get(idx).isBlackJack();
	}

	public LinkedHashMap<Participant, EarningRate> getResult(Dealer other) {
		LinkedHashMap<Participant, EarningRate> map = new LinkedHashMap<>();
		players.stream()
			.forEach(player -> map.put(player, player.getResult(other)));
		return map;
	}

	public int getNumberOfPlayers() {
		return players.size();
	}

	public String showName(int idx) {
		return players.get(idx).showName();
	}

	public List<String> showHand(int idx) {
		return players.get(idx).showHand();
	}

	public int showScore(int idx) {
		return players.get(idx).getScore();
	}

}
