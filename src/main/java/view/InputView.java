package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PARSE_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);
    public static final String POSITIVE_USER_INPUT = "Y";
    public static final String NEGATIVE_USER_INPUT = "N";

    private InputView() {
    }

    public static String input(String inputRequestMessage) {
        System.out.println(inputRequestMessage);
        return scanner.nextLine();
    }

    public static List<String> inputNames() {
        String initialInput = validateContainsDealerName(input("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"));
        return Arrays.stream(initialInput.split(INPUT_PARSE_DELIMITER))
                .map(String::trim)
                .toList();
    }

    public static boolean inputPlayerHitChoice(String inputRequestMessage) {
        String initialInput = input(inputRequestMessage + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return validateChoiceInputReturnDecision(initialInput);
    }

    private static boolean validateChoiceInputReturnDecision(String initialInput) {
        if (initialInput.equalsIgnoreCase(POSITIVE_USER_INPUT)) {
            return true;
        }
        if (initialInput.equalsIgnoreCase(NEGATIVE_USER_INPUT)) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 혹은 n만 입력 가능합니다.");
    }

    public static long inputBettingAmount(String targetName) {
        System.out.println();
        String initialInput = input(targetName + "의 배팅 금액은?");
        validateBettingAmount(initialInput);
        return Integer.parseInt(initialInput);
    }

    private static void validateBettingAmount(String initialInput) {
        if (initialInput.matches("\\d+")) {
            return;
        }
        throw new IllegalArgumentException("숫자만 입력가능합니다.");
    }

    private static String validateContainsDealerName(String inputName) {
        if (inputName.contains("딜러")) {
            throw new IllegalArgumentException("'딜러'가 포함된 사용자명은 사용할 수 없습니다.");
        }
        return inputName;
    }
}
