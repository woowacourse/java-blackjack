package view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    private static final String INVALID_DELIMITER_MESSAGE = "잘못된 구분자 입력입니다.";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> enterPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validatePlayerNames(input);
        return Arrays.asList(input.split(","));
    }

    private void validatePlayerNames(final String input) {
        isValidDelimiter(input);
        List<String> names = Arrays.asList(input.split(","));
        isNamesDuplicated(names);
    }

    private void isValidDelimiter(final String input) {
        isInputStartWithComma(input);
        isInputEndWithComma(input);
        isInputWithEmptyName(input);
    }

    private static void isInputStartWithComma(final String input) {
        if (input.startsWith(",")) {
            throw new IllegalArgumentException(INVALID_DELIMITER_MESSAGE);
        }
    }

    private static void isInputEndWithComma(final String input) {
        if (input.endsWith(",")) {
            throw new IllegalArgumentException(INVALID_DELIMITER_MESSAGE);
        }
    }

    private static void isInputWithEmptyName(final String input) {
        if (input.contains(",,")) {
            throw new IllegalArgumentException(INVALID_DELIMITER_MESSAGE);
        }
    }

    private void isNamesDuplicated(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (names.size() != distinctNames.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름입니다.");
        }
    }

    public String decideToGetMoreCard(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
