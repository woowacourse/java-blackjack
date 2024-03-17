package view;

import constants.ErrorCode;
import exception.InvalidBetAmountException;
import exception.InvalidInputException;
import exception.InvalidSeparatorException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_SEPARATOR = ",";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine();
        validateBlank(rawNames);
        validateSeparators(rawNames);
        List<String> names = Arrays.stream(rawNames.split(NAME_SEPARATOR))
                .map(String::trim)
                .toList();
        System.out.println();
        return names;
    }

    public Long readBetAmount(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
        String rawAmount = scanner.nextLine().trim();
        validateBlank(rawAmount);
        validateLong(rawAmount);
        System.out.println();
        return Long.parseLong(rawAmount);
    }


    public String readAnswer(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String rawAnswer = scanner.nextLine().trim();
        validateBlank(rawAnswer);
        return rawAnswer;
    }

    private void validateBlank(final String rawNames) {
        if (rawNames == null || rawNames.isBlank()) {
            throw new InvalidInputException(ErrorCode.INVALID_INPUT);
        }
    }

    private void validateSeparators(final String rawNames) {
        if (isInvalidSeparator(rawNames)) {
            throw new InvalidSeparatorException(ErrorCode.INVALID_SEPARATOR);
        }
    }

    private boolean isInvalidSeparator(final String rawNames) {
        if (rawNames.startsWith(NAME_SEPARATOR)) {
            return true;
        }
        if (rawNames.endsWith(NAME_SEPARATOR)) {
            return true;
        }
        return rawNames.contains(NAME_SEPARATOR.repeat(2));
    }

    private void validateLong(final String rawAmount) {
        try {
            Long.parseLong(rawAmount);
        } catch (NumberFormatException exception) {
            throw new InvalidBetAmountException(ErrorCode.INVALID_BET_AMOUNT);
        }
    }
}
