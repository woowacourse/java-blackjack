package blackjack.controller;

import static blackjack.view.InputView.*;
import static blackjack.view.OutputView.*;

import java.util.List;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.blackjack.DrawOpinion;
import blackjack.domain.result.Report;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class BlackjackController {
	private final BlackjackTable blackjackTable;

	public BlackjackController(BlackjackTable blackjackTable) {
		this.blackjackTable = blackjackTable;
	}

	public void playGame(Dealer dealer, List<Player> players) {
		List<User> users = blackjackTable.collectToUsersFrom(dealer, players);

		drawInitialCardsFrom(users);
		continueDrawCardsEach(dealer, players);

		printUsersCardsAndScore(users);

		Report blackJackReport = Report.from(dealer, players);
		printBlackjackReport(blackJackReport);
	}

	private void drawInitialCardsFrom(List<User> users) {
		blackjackTable.drawInitialCards(users);
		printUsersInitialDraw(BlackjackTable.INITIAL_DRAW_NUMBER, users);
	}

	private void continueDrawCardsEach(Dealer dealer, List<Player> players) {
		for (Player player : players) {
			drawCardsFrom(player);
		}
		drawCardsFrom(dealer);
	}

	private void drawCardsFrom(Player player) {
		while (canDraw(player) && wantDraw(player)) {
			blackjackTable.drawCardFrom(player);
			printUserHand(player, player.getHand());
		}
	}

	private boolean canDraw(User user) {
		return user.canDraw();
	}

	private boolean wantDraw(Player player) {
		return DrawOpinion.of(inputDrawOpinion(player))
			.isYes();
	}

	private void drawCardsFrom(Dealer dealer) {
		while (canDraw(dealer)) {
			blackjackTable.drawCardFrom(dealer);
			printDealerDrawCard();
		}
	}
}
