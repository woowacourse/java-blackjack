package service;

import domain.ScoreType;
import domain.card.Deck;
import domain.result.Result;
import domain.result.ResultType;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.HashMap;
import java.util.Map;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
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

	public static Result createResultWhenDealerBlackjack(Players players) {
		Map<Player, ResultType> results = new HashMap<>();
		players.forEach(player -> {
			if (isBlackjack(player)) {
				results.put(player, ResultType.DRAW);
				return;
			}
			results.put(player, ResultType.LOSE);
		});
		return new Result(results);
	}

	private static boolean isBlackjack(Player player) {
		return ScoreType.BLACKJACK.equals(ScoreType.of(player.openAllCards()));
	}
}
