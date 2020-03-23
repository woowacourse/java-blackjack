package view;

import domain.gamer.Name;
import domain.gamer.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);

	public static String inputUserNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return scanner.nextLine();
	}

	public static List<String> inputMoney(List<Name> names) {
		List<String> bettingMoney = new ArrayList<>();
		for (Name name : names) {
			System.out.println(name.getName() + "의 배팅 금액은?");
			bettingMoney.add(scanner.nextLine());
		}
		return bettingMoney;
	}

	public static String inputReceiveMore(Player player) {
		StringBuilder sb = new StringBuilder();
		sb.append(player.getName())
				.append("는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		System.out.println(sb);
		return scanner.nextLine();
	}
}