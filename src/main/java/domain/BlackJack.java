package domain;

import static domain.Judge.*;

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

	public Map<String, Integer> calculatePlayerProfits() {
		Map<String, Integer> playerProfit = new LinkedHashMap<>();
		List<Player> players = users.getPlayers();
		Dealer dealer = users.getDealer();
		for (Player player : players) {
			playerProfit.put(player.getName(), calculateProfit(player, dealer));
		}
		return playerProfit;
	}

	public int calculateDealerProfit() {
		int dealerProfit = 0;
		Map<String, Integer> playerProfits = calculatePlayerProfits();
		for (Integer playerProfit : playerProfits.values()) {
			dealerProfit -= playerProfit;
		}
		return dealerProfit;
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
