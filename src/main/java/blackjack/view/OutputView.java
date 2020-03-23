package blackjack.view;

import java.util.List;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.result.Report;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.util.StringUtil;

public class OutputView {
	static final String NEWLINE = System.getProperty("line.separator");
	private static final String INITIAL_DRAW_FORMAT = "%s와 %s에게 %d장의 나누었습니다.";
	private static final String DEALER_DRAW_FORMAT = "%s는 %d이하라 한장의 카드를 더 받았습니다.";
	private static final String FINAL_RESULT_MESSAGE = "## 최종 승패";
	private static final String SEPARATOR = ": ";

	public static void printUsersInitialDealtHand(int initialDrawNumber, List<User> users) {
		System.out.println(
			String.format(NEWLINE + INITIAL_DRAW_FORMAT,
				Dealer.NAME,
				StringUtil.joinPlayerNames(users),
				initialDrawNumber));
		users.forEach(user -> printUserHand(user, user.getInitialDealtHand()));
	}

	public static void printUserHand(User user, List<Card> hand) {
		System.out.println(user.getName() + SEPARATOR + StringUtil.joinCards(hand));
	}

	public static void printDealerDraw() {
		System.out.println(String.format(DEALER_DRAW_FORMAT, Dealer.NAME, Dealer.DEALER_DRAWABLE_MAX_SCORE));
	}

	public static void printUsersHandAndScore(List<User> users) {
		printNewLine();
		for (User user : users) {
			OutputView.printUserHandAndScore(user);
		}
	}

	public static void printUserHandAndScore(User user) {
		System.out.println(user.getName() + SEPARATOR
			+ StringUtil.joinCards(user.getHand())
			+ " - 결과: " + user.getScore());
	}

	public static void printBlackjackReport(Report report) {
		System.out.println(NEWLINE + FINAL_RESULT_MESSAGE);
		printUserResult(Dealer.NAME, report.getDealerBettingProfit());
		printPlayersResult(report.getPlayersProfit());
	}

	private static void printPlayersResult(Map<Player, Integer> playersResult) {
		for (Map.Entry<Player, Integer> entry : playersResult.entrySet()) {
			Player player = entry.getKey();
			int profit = entry.getValue();
			printUserResult(player.getName(), profit);
		}
	}

	private static void printUserResult(String name, int profit) {
		System.out.println(name + SEPARATOR + profit);
	}

	private static void printNewLine() {
		System.out.print(NEWLINE);
	}
}
