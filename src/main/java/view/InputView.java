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
	private static final String YES = "Y";
	private static final String NO = "N";

	public static String[] inputPlayersName() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.stream(scanner.nextLine()
			.split(DELIMITER, LIMIT))
			.map(String::trim)
			.toArray(String[]::new);
	}

	public static String inputMoreCard(PlayerDto playerDto) {
		System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerDto.getName()));
		return scanner.nextLine();
	}

	public static String inputBettingMoney() {
		return scanner.nextLine();
	}
}
