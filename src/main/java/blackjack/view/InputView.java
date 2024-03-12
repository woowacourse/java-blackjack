package blackjack.view;

import blackjack.view.object.Command;
import java.util.List;
import java.util.Scanner;

public class InputView {

	private final Scanner scanner;

	public InputView() {
		scanner = new Scanner(System.in);
	}

	public List<String> receivePlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String input = scanner.nextLine();
		return List.of(input.split(","));
	}

	public Command receiveCommand(String name) {
		System.out.println(name + "는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오: n)");
		return Command.convertInputToCommand(scanner.nextLine());
	}
}
