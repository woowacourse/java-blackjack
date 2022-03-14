package domain.participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.WinOrLose;

public class Players {

	private static final String NOT_BLACK_JACK_SITUATION_ERROR_MESSAGE = "[Error] BlackJack 이 없습니다.";

	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public List<Name> getNames() {
		return players.stream().map(Participant::getName).collect(Collectors.toList());
	}

	public void addCardByName(Name name, Card card) {
		players.stream().filter(player -> player.isNameMatch(name)).forEach(player -> player.addCard(card));
	}

	public List<String> showHands() {
		return players.stream().map(Player::showHand).collect(Collectors.toList());
	}

	public String showHandByName(Name name) {
		return players.stream()
			.filter(player -> player.isNameMatch(name))
			.map(Player::showHand).findFirst().orElseThrow();
	}

	public List<String> showHandsAndBestScores() {
		return players.stream().map(Player::showHandAndBestScore).collect(Collectors.toList());
	}

	public boolean checkAllBust() {
		long count = players.stream()
			.filter(Player::isBust)
			.count();
		return count == players.size();
	}

	public boolean checkBustByName(Name name) {
		return players.stream()
			.filter(player -> player.isNameMatch(name))
			.map((Player::isBust)).findFirst().orElseThrow();
	}

	public boolean checkMaxScoreByName(Name name) {
		return players.stream()
			.filter(player -> player.isNameMatch(name))
			.map((Player::isMaxScore)).findFirst().orElseThrow();
	}

	public Map<Name, WinOrLose> getResultAtBlackJack(Participant other) {
		if (!other.isBlackJack()) {
			throw new IllegalStateException(NOT_BLACK_JACK_SITUATION_ERROR_MESSAGE);
		}
		Map<Name, WinOrLose> map = new LinkedHashMap<>();
		players.stream().forEach(player -> map.put(player.name, player.compareAtBlackJack(other)));
		return map;
	}

	public Map<Name, WinOrLose> getResultAtFinal(Participant other) {
		Map<Name, WinOrLose> map = new LinkedHashMap<>();
		players.stream().forEach(player -> map.put(player.name, player.compareAtFinal(other)));
		return map;
	}
}
