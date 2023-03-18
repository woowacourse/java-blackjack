package domain;

import static domain.GameResult.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.card.Card;
import domain.card.Deck;

public class BlackJack {

	private final Users users;
	private final Deck deck;

	public BlackJack(final Users users, final Deck deck) {
		this.users = users;
		this.deck = deck;
	}

	public void giveCard(Player player) {
		player.hit(deck.pickCard());
	}

	public void giveCardToDealer() {
		Dealer dealer = users.getDealer();
		dealer.hit(deck.pickCard());
	}

	public Map<String, String> calculatePlayerResults() {
		Map<String, String> playerResult = new LinkedHashMap<>();
		List<Player> players = users.getPlayers();
		Dealer dealer = users.getDealer();
		int dealerScore = dealer.getScore();
		for (Player player : players) {
			playerResult.put(player.getName(), comparePlayerWithDealer(player.getScore(), dealerScore).getName());
		}
		return playerResult;
	}

	public Map<String, String> calculateDealerResult() {
		Map<String, Integer> result = new LinkedHashMap<>();
		Map<String, String> playerResults = calculatePlayerResults();
		calculateResults(result, playerResults);
		return joinWinning(result);
	}

	private void calculateResults(Map<String, Integer> result, Map<String, String> playerResults) {
		for (GameResult value : values()) {
			result.put(value.getName(), 0);
		}
		for (String winning : playerResults.values()) {
			String dealerWinning = convert(winning);
			result.replace(dealerWinning, result.getOrDefault(dealerWinning, 0) + 1);
		}
	}

	private String convert(String winning) {
		if (winning.equals(WIN.getName()))
			return LOSE.getName();
		if (winning.equals(LOSE.getName()))
			return WIN.getName();
		return PUSH.getName();
	}

	private Map<String, String> joinWinning(Map<String, Integer> result) {
		Map<String, String> dealerResult = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		for (String winning : result.keySet()) {
			join(result, sb, winning);
		}
		dealerResult.put("딜러", sb.toString());
		return dealerResult;
	}

	private static void join(Map<String, Integer> result, StringBuilder sb, String winning) {
		if (result.get(winning) > 0)
			sb.append(result.get(winning).toString()).append(winning).append(' ');
	}

	public boolean isDealerHittable() {
		return users.getDealer().isHittable();
	}

	public String getDealerCardHidden() {
		Card dealerCard = users.getDealer().getOneDealerCard();
		return dealerCard.getCardName();
	}

	public Map<String, List<String>> getPlayerToCard() {
		Map<String, List<String>> playerWithCards = new HashMap<>();
		for (Player player : users.getPlayers()) {
			playerWithCards.put(player.getName(), player.getCardNames());
		}

		return playerWithCards;
	}

	public Map<String, Integer> getPlayerToScore() {
		Map<String, Integer> playerWithScore = new LinkedHashMap<>();
		for (Player player : users.getPlayers()) {
			playerWithScore.put(player.getName(), player.getScore());
		}

		return playerWithScore;
	}

	public List<Player> getPlayers() {
		return users.getPlayers();
	}

	public List<String> getDealerCards() {
		return users.getDealer().getCardNames();
	}

	public int getDealerScore() {
		return users.getDealer().getScore();
	}
}
