package domain.participant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.participant.info.Name;
import domain.result.EarningRate;

public class Players {
	private final LinkedHashMap<Name, Player> players;

	public Players(List<Player> players) {
		LinkedHashMap playersMap = new LinkedHashMap<>();
		players.stream().forEach(player -> playersMap.put(player.getName(), player));
		this.players = playersMap;
	}

	public List<Name> getNames() {
		return new ArrayList<>(players.keySet());
	}

	public void addCardByName(Name name, Card card) {
		players.get(name).addCard(card);
	}

	public boolean checkAllBust() {
		long count = players.keySet().stream()
			.filter(key -> players.get(key).isBust())
			.count();
		return count == players.size();
	}

	public boolean checkBustByName(Name name) {
		return players.get(name).isBust();
	}

	public boolean checkMaxScoreByName(Name name) {
		return players.get(name).isMaxScore();
	}

	public LinkedHashMap<Participant, EarningRate> getResult(Dealer other) {
		LinkedHashMap<Participant, EarningRate> map = new LinkedHashMap<>();
		players.keySet().stream()
			.forEach(name -> map.put(players.get(name), players.get(name).getResult(other)));
		return map;
	}

	public Participant getPlayerInfoByName(Name name) {
		return players.get(name);
	}

	public List<Player> getPlayerInfo() {
		return players.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
	}
}
