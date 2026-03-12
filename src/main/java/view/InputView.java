package view;

import domain.Money;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static final String PARTICIPANT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String ERROR_NAMES_EMPTY = "[ERROR] 이름은 비어있을 수 없습니다.";
    public static final String ERROR_INCORRECT_CHOICE = "[ERROR] y 또는 n을 입력해주세요.";
    private static final String YES = "y";
    private static final String NO = "n";
    public static final String ONE_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    public static final String DELIMITER = ",";
    public static final int ZERO = 0;

    public List<String> getParsedNames() {
        System.out.println(PARTICIPANT_NAME_MESSAGE);
        String input = scanner.nextLine();
        return parseNames(input);
    }

    List<String> parseNames(String input) {
        validateInputNull(input);

        List<String> names = Arrays.stream(input.split(DELIMITER))
                .map(String::strip)
                .collect(Collectors.toList());

        validateEmptyInput(names);

        return names;
    }

    public boolean askForOneMoreCard(String name) {
        System.out.println(name + ONE_MORE_CARD_MESSAGE);
        String userChoice = scanner.nextLine();
        validateUserChoice(userChoice);
        return YES.equals(userChoice);
    }

    private void validateEmptyInput(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NAMES_EMPTY);
        }
    }

    private void validateInputNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ERROR_NAMES_EMPTY);
        }
    }

    private void validateUserChoice(String userChoice) {
        if (!YES.equals(userChoice) && !NO.equals(userChoice)) {
            throw new IllegalArgumentException(ERROR_INCORRECT_CHOICE);
        }
    }

    public Money parseMoney(String input) {
        String strippedInput = input.strip();

        try {
            int amount = Integer.parseInt(strippedInput);
            validateNegativeAmount(amount);
            return new Money(amount);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateNegativeAmount(int amount) {
        if (amount <= ZERO) {
            throw new IllegalArgumentException();
        }
    }
}
