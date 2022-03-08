package blackjack.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    private static final String NULL_NAMES_ERROR_MESSAGE = "이름에 공백을 입력할 수 없습니다.";
    private static final String NULL_NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";
    private static final String NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_SEPARATE_REGEX = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String[] inputPlayerName() {
        System.out.println(NAME_INPUT_MESSAGE);
        String input = SCANNER.nextLine();
        validateName(input);
        return separate(input);
    }

    private static void validateName(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(NULL_NAMES_ERROR_MESSAGE);
        }
        String[] names = separate(input);
        Set<String> nameSet = new HashSet<>(Arrays.asList(names));
        System.out.println(nameSet);
        if (nameSet.size() != names.length) {
            throw new IllegalArgumentException(NULL_NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }

    private static String[] separate(String input) {
        return input.split(NAME_SEPARATE_REGEX);
    }
    // split 검증 view
    // 이름은 name 클래스 만들어서
}
