package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Players;

import static java.util.stream.Collectors.joining;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class OutputView {

	private static final String DELIMITER = ", ";

	public static void printInitialDistribution(Players players) {
		System.out.println("딜러와 " + players.getNames() + "에게 2장의 카드를 나누었습니다.");
	}

	public static void printInitialStatus(Dealer dealer, Players players) {
		System.out.println("딜러: " + dealer.openCard().toString());
		players.forEach(player -> {
			System.out.println(
					player.toString() +
							"카드: " +
							player.openAllCards().stream()
									.map(Card::toString)
									.collect(joining(DELIMITER))
			);
		});
	}
}
