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
        validateInputDelimiter(userNames);
        return Arrays.stream(userNames.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    private static void validateInputDelimiter(String input) {
        if (input.startsWith(DELIMITER) || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("입력은 구분자로 시작하거나 끝날 수 없습니다.");
        }
    }

    public static String inputHitOption(Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name.getValue());
        System.out.println();
        return SCANNER.nextLine();
    }
}
