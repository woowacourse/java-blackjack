package domain.participant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.WinOrLose;

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

	public Map<Name, WinOrLose> getResult(Dealer other) {
		Map<Name, WinOrLose> map = new LinkedHashMap<>();
		players.keySet().stream()
			.forEach(name -> map.put(name, players.get(name).getResult(other)));
		return map;
	}

	public ParticipantInfo getPlayerInfoByName(Name name) {
		return new ParticipantInfo(players.get(name));
	}

	public List<ParticipantInfo> getPlayerInfo() {
		return players.keySet().stream()
			.map(name -> new ParticipantInfo(players.get(name)))
			.collect(Collectors.toList());
	}
}
