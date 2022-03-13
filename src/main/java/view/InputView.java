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
	public static final String YES = "y";
	public static final String NO = "n";
	public static final String INPUT_ASKING_DRAW_ERROR_MESSAGE = "[Error] y나 n만 입력할 수 있습니다.";

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

	public static boolean askDraw(String name) {
		System.out.printf(INPUT_ASk_DRAW_MESSAGE_FORMAT, name);
		try {
			String resultAskDraw = scanner.nextLine();
			validateAskDraw(resultAskDraw);
			return giveOpinion(resultAskDraw);
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return askDraw(name);
		}
	}

	private static void validateAskDraw(String resultAsk) {
		if (!(resultAsk.equals(YES) || resultAsk.equals(NO))) {
			throw new IllegalArgumentException(INPUT_ASKING_DRAW_ERROR_MESSAGE);
		}
	}

	private static boolean giveOpinion(String resultAskDraw) {
		if (resultAskDraw.equals(YES)) {
			return true;
		}
		return false;
	}

}
