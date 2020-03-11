package controller;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;
import service.BlackjackService;

import static view.OutputView.printInitialDistribution;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class BlackjackController {
	public static void run(Deck deck, Dealer dealer, Players players) {
		BlackjackService.giveInitialCards(deck, dealer, players);
		printInitialDistribution(players);
	}
}
