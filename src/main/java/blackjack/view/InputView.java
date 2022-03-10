package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateEmpty(input);
        return Arrays.asList(input.split(NAME_DELIMITER, -1));
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈 문자는 입력할 수 없습니다.");
        }
    }
}
