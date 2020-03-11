package controller;

import domain.ScoreType;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;
import service.BlackjackService;

import static view.OutputView.*;

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
		printInitialStatus(dealer, players);
		if (isBlackjack(dealer)) {
			printResult(BlackjackService.createResultWhenDealerBlackjack(players), players);
		}
	}

	private static boolean isBlackjack(Dealer dealer) {
		return ScoreType.BLACKJACK.equals(ScoreType.of(dealer.openAllCards()));
	}
}
