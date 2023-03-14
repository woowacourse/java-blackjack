package view;

import common.ExecuteContext;
import common.ExecuteStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String BLANK_INPUT_INVALID_ERROR_MESSAGE = "빈 값은 허용되지 않습니다.";
    private static final String DELIMITER = ",";
    private static final String INPUT_NAMES_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_CARD_INTENT_REQUEST_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INPUT_CHECK_LETTER_ERROR_MESSAGE = "허용되지 않는 입력입니다.";
    private static final String INPUT_BATTING_REQUEST_MESSAGE = "%s의 배팅 금액은?";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String NEW_LINE = "\n";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "숫자만 입력할 수 있습니다.";
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> inputNames() {
        return ExecuteContext.workWithExecuteStrategy(() -> {
                System.out.println(INPUT_NAMES_REQUEST_MESSAGE);
                final String input = scanner.nextLine().replace(" ", "");
                checkBlank(input);
                return Arrays.stream(input.split(DELIMITER))
                    .collect(Collectors.toList());
            }
        );
    }

    private void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT_INVALID_ERROR_MESSAGE);
        }
    }

    public boolean inputCardIntent(final String name) {
        return ExecuteContext.workWithExecuteStrategy(() -> {
                System.out.printf(NEW_LINE + INPUT_CARD_INTENT_REQUEST_MESSAGE + NEW_LINE, name);
                final String input = scanner.nextLine();
                checkLetter(input, YES, NO);
                return input.equals(YES);
            }
        );
    }

    private void checkLetter(final String input, final String... expectedLetters) {
        Arrays.stream(expectedLetters)
            .filter(input::equals)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(INPUT_CHECK_LETTER_ERROR_MESSAGE));
    }

    public List<Double> inputBattings(final List<String> names) {
        final List<Double> battings = new ArrayList<>();
        names.stream()
            .<ExecuteStrategy<Boolean>>map(name -> () -> {
                System.out.printf(NEW_LINE + INPUT_BATTING_REQUEST_MESSAGE + NEW_LINE, name);
                return battings.add(getBatting());
            })
            .forEach(ExecuteContext::workWithExecuteStrategy);
        return battings;
    }

    private double getBatting() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }
}
