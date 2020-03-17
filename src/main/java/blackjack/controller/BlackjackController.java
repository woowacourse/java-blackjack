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
		List<User> users = blackjackTable.collectUsers();
		blackjackTable.dealInitialHand();
		OutputView.printUsersInitialDealtHand(BlackjackTable.INITIAL_DEAL_NUMBER, users);

		UserDecisions userDecisions = new UserDecisions(InputView::inputChoiceFromPlayer, OutputView::printUserHand,
			OutputView::printDealerDraw);
		blackjackTable.playWith(userDecisions);

		Report blackJackReport = Report.from(blackjackTable);
		OutputView.printUsersHandAndScore(users);
		OutputView.printBlackjackReport(blackJackReport);
	}
}
