package blackjack.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.blackjack.UserDecisions;
import blackjack.domain.result.BettingMoney;
import blackjack.domain.result.Report;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
	private final BlackjackTable blackjackTable;

	public BlackjackController(BlackjackTable blackjackTable) {
		Objects.requireNonNull(blackjackTable, "블랙잭 테이블이 존재하지 않습니다.");
		this.blackjackTable = blackjackTable;
	}

	public void play(Map<Player, BettingMoney> playersBettingMoney) {
		Objects.requireNonNull(playersBettingMoney, "플레이어들의 배팅 머니가 존재하지 않습니다.");
		List<User> users = blackjackTable.collectUsers();

		dealInitialHand(users);
		if (!blackjackTable.isDealerBlackjack()) {
			playContinue();
		}
		printBlackjackReport(users, playersBettingMoney);
	}

	private void dealInitialHand(List<User> users) {
		blackjackTable.dealInitialHand();
		OutputView.printUsersInitialDealtHand(BlackjackTable.INITIAL_DEAL_NUMBER, users);
	}

	private void playContinue() {
		UserDecisions userDecisions = new UserDecisions(InputView::inputChoiceFrom, OutputView::printUserHand,
			OutputView::printDealerDraw);
		blackjackTable.playWith(userDecisions);
	}

	private void printBlackjackReport(List<User> users, Map<Player, BettingMoney> playersBettingMoney) {
		Report blackJackReport = Report.from(blackjackTable, playersBettingMoney);
		OutputView.printUsersHandAndScore(users);
		OutputView.printBlackjackReport(blackJackReport);
	}
}
