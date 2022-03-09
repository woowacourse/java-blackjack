package view;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

	public static void printHand(List<String> names, String dealerHand, List<String> playerHands) {
		String namesForPrint = names.stream().collect(Collectors.joining(", "));
		System.out.printf("딜러와 %s에게 2장의 나누었습니다.\n", namesForPrint);
		System.out.println(dealerHand);
		playerHands.stream().forEach(System.out::println);
	}
}
