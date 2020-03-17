package view;

import domain.player.User;

import java.util.Scanner;

public class InputView {
	private static final String MESSAGE_INPUT_USER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표(,)기준으로 분리)";
	private static final String STRING_FORMAT_IS_HIT = "\n%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
	private static Scanner scanner = new Scanner(System.in);

	public static String inputUserNames() {
		System.out.println(MESSAGE_INPUT_USER_NAME);
		return scanner.next();
	}

	public static String inputIsHit(User user) {
		System.out.println(String.format(STRING_FORMAT_IS_HIT, user.getName()));
		return scanner.next();
	}

	public static String inputMoney(String name) {
		System.out.println(String.format("%s의 베팅금액은?", name));
		return scanner.next();
	}
}
