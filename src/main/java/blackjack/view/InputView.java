package blackjack.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    private static final String NULL_NAMES_ERROR_MESSAGE = "[ERROR] 이름에 공백은 포함될 수 없습니다..";
    private static final String NAMES_DUPLICATED_ERROR_MESSAGE = "[ERROR] 중복된 이름을 입력할 수 없습니다.";
    private static final String NULL_ANSWER_INPUT_ERROR_MESSAGE = "[ERROR] y,n 이외의 문자를 입력할 수 없습니다.";
    private static final String NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ONE_MORE_CARD_INPUT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String NAME_SEPARATE_REGEX = ",";
    private static final String YES_ANSWER = "y";
    private static final String NO_ANSWER = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String[] inputGamblerName() {
        System.out.println(NAME_INPUT_MESSAGE);
        String input = SCANNER.nextLine();
        validateNames(input);
        return separate(input);
    }

    public static boolean inputOneMoreCard(String name) {
        System.out.printf(ONE_MORE_CARD_INPUT_MESSAGE + System.lineSeparator(), name);
        String input = SCANNER.nextLine();
        validateAnswer(input);
        return input.equals(YES_ANSWER);
    }

    private static void validateAnswer(String input) {
        if (!input.equals(YES_ANSWER) && !input.equals(NO_ANSWER)) {
            throw new IllegalArgumentException(NULL_ANSWER_INPUT_ERROR_MESSAGE);
        }
    }

    private static void validateNames(String input) {
        validateNullNameInput(input);
        validateDuplicatedNameInput(input);
    }

    private static void validateNullNameInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(NULL_NAMES_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicatedNameInput(String input) {
        String[] names = separate(input);
        Set<String> nameSet = new HashSet<>(Arrays.asList(names));
        if (nameSet.size() != names.length) {
            throw new IllegalArgumentException(NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }

    private static String[] separate(String input) {
        return input.split(NAME_SEPARATE_REGEX);
    }
}
