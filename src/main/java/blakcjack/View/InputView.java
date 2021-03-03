package blakcjack.View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static List<String> takePlayerNamesInput() {
		return Arrays.stream(SCANNER.nextLine().split(","))
				.map(String::trim)
				.collect(Collectors.toList());
	}

	public static String takeHitOrStand(String name) {
		System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n");
		return SCANNER.nextLine().trim();
	}
}
