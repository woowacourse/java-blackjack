package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public List<String> readPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String names = scanner.nextLine().replace(" ", "");

		if (names.startsWith(",") || names.endsWith(",")) {
			throw new IllegalArgumentException();
		}
		return List.of(names.split(","));
	}
}
