package blackjack.view;

import java.util.List;

import blackjack.domain.user.User;
import blackjack.util.StringUtil;

public class OutputView {
	private static final String NEWLINE = System.getProperty("line.separator");
	private static final String INITIAL_DRAW_FORMAT = "dealer와 %s에게 %d장의 나누었습니다.";

	public static void printUsersInitialDraw(int initialDrawNumber, List<User> users) {
		System.out.println(
			String.format(NEWLINE + INITIAL_DRAW_FORMAT, StringUtil.joinPlayerNames(users), initialDrawNumber));

		for (User user : users) {
			System.out.println(user.getName() + ": " + StringUtil.joinCards(user.getInitialHand()));
		}
	}
}
