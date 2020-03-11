package view;

import domain.Result;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;

import java.util.Map;

public class OutputView {
	public static void firstDrawMessage(String name, int firstDrawCount) {
		System.out.println(String.format("딜러와 %s에게 %d장씩 나누었습니다.", name, firstDrawCount));
	}

	public static void printOneCard(Dealer dealer) {
		System.out.println(dealer.toStringOneCard());
	}

	public static void printCardStatus(User user) {
		System.out.println(user.toString());
	}

	public static void printDealerAdditionalCard() {
		System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
	}

	public static void printFinalScore(Player player) {
		System.out.println(player.toString() + " -  결과 : " + player.calculateScore());
	}

	public static void printFinalResult() {
		System.out.println("\n## 최종 승패");

	}

	public static void printDealerResult(int dealerWin, int dealerDraw, int dealerLose) {
		System.out.println("딜러: " + dealerWin + "승 " + dealerDraw + "무 " + dealerLose + "패");
	}

	public static void printUserResult(Map.Entry<String, Result> userResultEntry) {
		System.out.println(userResultEntry.getKey() + ": " + userResultEntry.getValue());

	}
}
