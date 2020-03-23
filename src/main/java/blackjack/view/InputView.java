package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import blackjack.player.domain.Player;
import blackjack.view.dto.DrawRequestDTO;

public class InputView {
	private static final String DELIMITER = ",";
	private final Scanner scanner;

	public InputView(Scanner scanner) {
		this.scanner = scanner;
	}

	public List<String> inputPlayNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.stream(scanner.nextLine()
			.split(DELIMITER))
			.collect(Collectors.toList());
	}

	public String inputBettingMoney(String name) {
		System.out.println(String.format("%s의 배팅 금액은?", name));
		return scanner.nextLine();
	}

	public DrawRequestDTO inputDrawRequest(Player player) {
		System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
		return new DrawRequestDTO(scanner.nextLine());
	}
}
