package domain.participant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.WinOrLose;

public class Players {
	private static final String NOT_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] BlackJack 이 없습니다.";

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

	public Map<Name, WinOrLose> getResultAtBlackJack(Participant other) {
		if (!other.isBlackJack()) {
			throw new IllegalStateException(NOT_BLACK_JACK_SITUATION_ERROR_MESSAGE);
		}
		Map<Name, WinOrLose> map = new LinkedHashMap<>();
		players.keySet().stream().forEach(name -> map.put(name, players.get(name).compareAtBlackJack(other)));
		return map;
	}

	public Map<Name, WinOrLose> getResultAtFinal(Participant other) {
		Map<Name, WinOrLose> map = new LinkedHashMap<>();
		players.keySet().stream()
			.forEach(name -> map.put(name, players.get(name).compareAtFinal(other)));
		return map;
	}

	public List<List<String>> getCardsOfAll() {
		return players.keySet().stream()
			.map(name -> players.get(name).getHand())
			.map(cards -> cards.stream().map(Card::getCardInfo).collect(Collectors.toList()))
			.collect(Collectors.toList());
	}

	public List<String> getCardsByName(Name name) {
		return players.get(name).getHand().stream()
			.map(Card::getCardInfo)
			.collect(Collectors.toList());
	}

	public List<Integer> getScores() {
		return players.keySet().stream()
			.map(name -> players.get(name).getBestScore())
			.collect(Collectors.toList());
	}
}
