package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import blackjack.player.Player;

public class InputView {
	private static final String DELIMITER = ",";
	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public List<String> inputPlayNames() {

		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		String inputNames = this.scanner.nextLine();
		validate(inputNames);
		return Arrays.stream(inputNames.split(DELIMITER))
			.collect(Collectors.toList());
	}

	private void validate(String inputNames) {
		if (inputNames == null || inputNames.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("%s 비어있는 값을 입력했습니다.", inputNames));
		}
	}

	public String inputDrawRequest(Player player) {
		System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
		return scanner.nextLine();
	}
}
