package view;

import domain.PlayerMoneys;
import domain.card.Card;
import domain.card.Cards;
import domain.user.Player;
import domain.user.Users;
import domain.user.User;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Map;

public class OutputView {
	private static final String DELIMITER = ", ";

	public static void printInitialDistribution(List<Player> players) {
		System.out.println("딜러와 " + players.stream().map(Player::toString).collect(joining(DELIMITER)) + "에게 2장의 카드를 나누었습니다.");
		emptyLine();
	}

	public static void printInitialStatus(User user) {
		System.out.println(user + ": " + user.openInitialCards().toList().stream().map(Card::toString).collect(joining(DELIMITER)));
	}

	public static void printCardsStatusOf(Player player) {
		Cards cards = player.openAllCards();
		System.out.println(player + "카드: " +
			cards.toList().stream()
				.map(Card::toString)
				.collect(joining(DELIMITER)));
	}

	public static void printDealerDrawing() {
		emptyLine();
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printResultStatus(Users users) {
		for (User user : users) {
			Cards cards = user.openAllCards();
			System.out.println(user + "카드: " +
				cards.toList().stream()
					.map(Card::toString)
					.collect(joining(DELIMITER)) +
				" - 결과: " + cards.getPoint());
		}
	}

	public static void printResultProfit(PlayerMoneys playerMoneys, User dealer) {
		Map<User, Double> totalPrizes = playerMoneys.getTotalPrizes(dealer);
		emptyLine();
		System.out.println("## 최종 수익");
		totalPrizes.forEach(((user, money) -> System.out.println( user + ": " + money)));
	}

	private static void emptyLine() {
		System.out.println();
	}
}
