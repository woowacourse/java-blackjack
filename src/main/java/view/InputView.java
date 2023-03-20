package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Player;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String YES = "y";
	private static final String NO = "n";

	private InputView() {
	}

	public static List<String> askPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String input = scanner.nextLine();
		return Stream.of(input.split(",", -1))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	public static double askPlayerBet(String name){
		System.out.println();
		System.out.println(name + "의 배팅 금액은?");
		String input = scanner.nextLine();
		return Double.parseDouble(input);
	}

	public static void blank(){
		System.out.println();
	}

	public static boolean askIfHit(final Player player) {
		System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		String input = scanner.nextLine().strip();
		if (!input.equalsIgnoreCase(YES) && !input.equalsIgnoreCase(NO)) {
			throw new IllegalArgumentException("y또는 n을 입력해주세요");
		}
		return input.equalsIgnoreCase(YES);
	}
}
