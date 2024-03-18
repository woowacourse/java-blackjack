package view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    private static final String PLAYER_NAMES_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_SEPARATOR = ",";
    private static final String REQUEST_BETTING_MONEY_MESSAGE = "%n%s의 배팅 금액은?%n";
    private static final String INVALID_DELIMITER_MESSAGE = "잘못된 구분자 입력입니다.";
    private static final String REQUEST_WHETHER_GET_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String DUPLICATED_NAME_MESSAGE = "중복된 플레이어 이름입니다.";
    private static final String INVALID_NUMBER_MESSAGE = "올바른 숫자를 입력해주세요.";
    private static final String INVALID_BLANK_INPUT_MESSAGE = "빈 문자열이 입력되었습니다.";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> enterPlayerNames() {
        System.out.println(PLAYER_NAMES_REQUEST_MESSAGE);
        String input = scanner.nextLine();
        validateBlankInput(input);
        validatePlayerNames(input);
        return Arrays.asList(input.split(NAME_SEPARATOR));
    }

    public int enterBettingMoney(final String playerName) {
        System.out.printf(REQUEST_BETTING_MONEY_MESSAGE, playerName);
        String input = scanner.nextLine();
        validateBlankInput(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_NUMBER_MESSAGE);
        }
    }

    private static void validateBlankInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_BLANK_INPUT_MESSAGE);
        }
    }

    private void validatePlayerNames(final String input) {
        isValidDelimiter(input);
        List<String> names = Arrays.asList(input.split(NAME_SEPARATOR));
        isNamesDuplicated(names);
    }

    private void isValidDelimiter(final String input) {
        isInputStartWithComma(input);
        isInputEndWithComma(input);
        isInputWithEmptyName(input);
    }

    private static void isInputStartWithComma(final String input) {
        if (input.startsWith(NAME_SEPARATOR)) {
            throw new IllegalArgumentException(INVALID_DELIMITER_MESSAGE);
        }
    }

    private static void isInputEndWithComma(final String input) {
        if (input.endsWith(NAME_SEPARATOR)) {
            throw new IllegalArgumentException(INVALID_DELIMITER_MESSAGE);
        }
    }

    private static void isInputWithEmptyName(final String input) {
        if (input.contains(NAME_SEPARATOR.repeat(2))) {
            throw new IllegalArgumentException(INVALID_DELIMITER_MESSAGE);
        }
    }

    private void isNamesDuplicated(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (names.size() != distinctNames.size()) {
            throw new IllegalArgumentException(DUPLICATED_NAME_MESSAGE);
        }
    }

    public String requestCommandWhetherGetMoreCard(final String name) {
        System.out.printf(REQUEST_WHETHER_GET_MORE_CARD, name);
        return scanner.nextLine();
    }
}
