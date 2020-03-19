package com.blackjack.view;

import java.util.Scanner;

import com.blackjack.domain.user.User;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	private InputView() {
	}

	public static String inputPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
		return SCANNER.nextLine();
	}

	public static int inputBettingMoney(String playerName) {
		System.out.printf("%s의 베팅 금액은?\n", playerName);
		return Integer.parseInt(SCANNER.nextLine());
	}

	public static String inputDrawDecideType(User player) {
		System.out.printf("%s은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
		return SCANNER.nextLine();
	}
}
