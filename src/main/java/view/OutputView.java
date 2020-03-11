package view;

import java.util.ArrayList;
import java.util.List;

import domain.*;

public class OutputView {
	private static final String DELIMITER = ", ";
	private static final String INIT_DISTRIBUTE_COUNT = "2";
	public static void printDistributeMessage(Players players){
		List<String> playersName = new ArrayList<>();
		players.forEach(player -> playersName.add(player.getName()));

		System.out.println("딜러와 " +String.join(DELIMITER,playersName) +"에게 "+ INIT_DISTRIBUTE_COUNT +"장 나누었습니다.");
	}

	public static void printInitStatus(Dealer dealer, Players players) {
		printStatus(dealer.getName(), dealer.getFirstCard());
		players.forEach(player -> printStatus(player.getName(), player.getCards()));
	}

	private static void printStatus(String name, List<Card> cards) {
		System.out.printf("%s: %s\n", name, cards);
	}

	public static void printUsersResult(Dealer dealer, Players players) {
		printUserResult(dealer);
		players.forEach(OutputView::printUserResult);
	}

	private static void printUserResult(User user) {
		System.out.printf("%s: %s - 결과: %d\n", user.getName(), user.getCards(), user.getScore());
	}
}
