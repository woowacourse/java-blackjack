package view;

import java.util.Arrays;
import java.util.Scanner;

import view.dto.PlayerDto;

/**
 *   class inputView입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class InputView {
	private static final String DELIMITER = ",";
	private static final int LIMIT = -1;
	private static final Scanner scanner = new Scanner(System.in);

	public static String[] inputPlayersName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.stream(scanner.nextLine()
			.split(DELIMITER, LIMIT))
			.map(String::trim)
			.toArray(String[]::new);
	}

	public static String inputMoreCard(PlayerDto playerDto) {
		System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerDto.getName()));
		try {
			String input = scanner.nextLine();

			validateYesOrNo(input);
			return input;
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e);
			return inputMoreCard(playerDto);
		}
	}

	private static void validateYesOrNo(String input) {
		boolean isException = (!"Y".equalsIgnoreCase(input)) && (!"N".equalsIgnoreCase(input));

		if (isException) {
			throw new IllegalArgumentException("y나 n을 입력해주세요.");
		}
	}
}
