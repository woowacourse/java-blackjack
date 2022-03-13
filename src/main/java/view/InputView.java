package view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

	private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
	private static final String INPUT_ASk_DRAW_MESSAGE_FORMAT = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
	private static final String INPUT_NAMES_SPLIT_DELIMITER = ",";
	private static final String NAME_DUPLICATE_ERROR_MESSAGE = "[Error] 이름은 중복일 수 없습니다.";

	private static Scanner scanner = new Scanner(System.in);

	public static List<String> inputNames() {
		System.out.println(INPUT_NAME_MESSAGE);
		try {
			String inputNames = scanner.nextLine();
			List<String> names = Arrays.stream(inputNames.split(INPUT_NAMES_SPLIT_DELIMITER))
				.map(String::trim)
				.collect(Collectors.toList());
			checkDuplicate(names);
			return names;
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return inputNames();
		}
	}

	private static void checkDuplicate(List<String> names) {
		if (new HashSet<>(names).size() != names.size()) {
			throw new IllegalArgumentException(NAME_DUPLICATE_ERROR_MESSAGE);
		}
	}

	public static String inputAskDraw(String name) {
		System.out.printf(INPUT_ASk_DRAW_MESSAGE_FORMAT, name);
		return scanner.nextLine();
	}
}
