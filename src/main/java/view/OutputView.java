package view;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

	public static void printHands(List<String> names, String dealerHand, List<String> playerHands) {
		String namesForPrint = names.stream().collect(Collectors.joining(", "));
		System.out.printf("\n딜러와 %s에게 2장의 나누었습니다.\n", namesForPrint);
		System.out.println(dealerHand);
		playerHands.stream().forEach(System.out::println);
	}

	public static void printHand(String hand) {
		System.out.println(hand);
	}

	public static void printBustMessage() {
		System.out.println("Bust!!!");
	}

	public static void printBlackJackMessage() {
		System.out.println("Black Jack!!!");
	}

	public static void printResultTitle() {
		System.out.println("\n## 최종 승패");
	}

	public static void printDealerResult(int winCount, int drawCount, int loseCount) {
		System.out.printf("딜러: %d승 %d무 %d패\n", winCount, drawCount, loseCount);
	}

	public static void printPlayerResult(String name, String result) {
		System.out.printf("%s: %s\n", name, result);
	}

	public static void printDealerDrawMessage() {
		System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}
}
