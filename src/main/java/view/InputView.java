package view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);
	private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String INPUT_PLAYER_MORE_DRAW_RESPONSE_MESSAGE = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

	private InputView() {
	}

	public static String inputNames() {
		System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
		return SCANNER.nextLine();
	}

	public static String inputMoreDrawResponse(String name) {
		System.out.printf(INPUT_PLAYER_MORE_DRAW_RESPONSE_MESSAGE, name);
		return SCANNER.nextLine();
	}
}
