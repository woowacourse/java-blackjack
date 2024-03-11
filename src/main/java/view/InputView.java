package view;

import domain.participant.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {

    }

    public static List<String> inputParticipantName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();
        return parseName(input);
    }

    private static List<String> parseName(final String userNames) {
        validate(userNames);
        return Arrays.stream(userNames.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    private static void validate(String input) {
        if (input.startsWith(DELIMITER) || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("입력은 구분자로 시작하거나 끝날 수 없습니다.");
        }
    }

    public static int inputBetAmount(Name name) {
        System.out.printf("%s의 배팅 금액은?", name.getValue());
        System.out.println();
        String input = SCANNER.nextLine();
        validateNumeric(input);
        validateRange(input);
        return Integer.parseInt(input);
    }

    private static void validateNumeric(String input) {
        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException("정수로 입력해주세요.");
        }
    }

    private static void validateRange(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정상적인 범위의 수를 입력해주세요.");
        }
    }

    public static String inputHitOption(Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name.getValue());
        System.out.println();
        return SCANNER.nextLine();
    }
}
