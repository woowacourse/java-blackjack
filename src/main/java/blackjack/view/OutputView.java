package blackjack.view;

import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.stream.Collectors;

public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printInitialInfo(User dealer, Players players) {
		String playerNames = players.getPlayers().stream()
				.map(User::getName)
				.collect(Collectors.joining(", "));

		System.out.printf("%s와 %s에게 %d장을 나누었습니다." + NEW_LINE,
				dealer.getName(), playerNames, dealer.countCards());
	}
}
