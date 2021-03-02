package blakcjack.View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static List<String> takePlayerNamesInput() {
		return Arrays.stream(scanner.nextLine().split(","))
		.map(String::trim)
		.collect(Collectors.toList());
	}
}
