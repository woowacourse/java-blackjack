package service;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;

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
}
