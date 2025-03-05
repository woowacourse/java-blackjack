package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
	private static final String NAME_DELIMITER = ",";

	private final Scanner sc;

	public InputView() {
		this.sc = new Scanner(System.in);
	}

	public List<String> readPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		final String input = sc.nextLine();
		return Arrays.asList(input.split(NAME_DELIMITER));
	}
}
