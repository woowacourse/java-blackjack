package service;

import domain.card.CardRepository;
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
	public Deck getShuffledDeck() {
		Deck deck = new Deck(CardRepository.toList());
		deck.shuffle();
		return deck;
	}

	public Dealer createDealer() {
		return new Dealer();
	}

	public void addCard(User user, Deck deck) {
		user.addCard(deck.pop());
	}

	public Result createResult(Dealer dealer, Players players) {
		Map<Player, ResultType> results = new HashMap<>();
		players.forEach(player -> results.put(player, ResultType.from(player, dealer)));
		return new Result(results);
	}
}
