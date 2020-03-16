package blackjack.controller;

import java.util.List;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.blackjack.UserDecisions;
import blackjack.domain.result.Report;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private final BlackjackTable blackjackTable;

	public BlackjackController(BlackjackTable blackjackTable) {
		this.blackjackTable = blackjackTable;
	}

	public void run() {
		List<User> users = blackjackTable.collectToUsers();
		UserDecisions userDecisions =
			new UserDecisions(InputView::inputChoiceFrom, OutputView::printUserHand, OutputView::printDealerDrawCard);

		blackjackTable.setUp();
		OutputView.printUsersInitialDraw(BlackjackTable.INITIAL_DRAW_NUMBER, users);

		blackjackTable.playWith(userDecisions);

		Report blackJackReport = Report.from(blackjackTable);
		OutputView.printUsersCardsAndScore(users);
		OutputView.printBlackjackReport(blackJackReport);
	}
}
