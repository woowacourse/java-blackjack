package service;

import domain.card.Deck;
import domain.result.Result;
import domain.result.ResultType;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;

import java.util.HashMap;
import java.util.Map;

public class BlackjackService {
	public static void giveInitialCards(Deck deck, Dealer dealer, Players players) {
		deck.shuffle();

		dealer.addCard(deck.pop());
		dealer.addCard(deck.pop());

		players.forEach(player -> {
			player.addCard(deck.pop());
			player.addCard(deck.pop());
		});
	}

	public static void addCard(User user, Deck deck) {
		user.addCard(deck.pop());
	}

	public static Result createResult(Dealer dealer, Players players) {
		Map<Player, ResultType> results = new HashMap<>();
		players.forEach(player -> results.put(player, ResultType.of(player, dealer)));
		return new Result(results);
	}
}
