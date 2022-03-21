package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class InputView {
    private static final String NULL_NAMES_ERROR_MESSAGE = "1개 이상의 이름을 입력해주세요.";
    private static final String NAME_SIZE_MIN_ERROR_MESSAGE = "이름은 각각 한자 이상 입력되어야 합니다.";
    private static final String NULL_ANSWER_YN_ERROR_MESSAGE = "y,n 이외의 입력이 들어왔습니다.";
    private static final String NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BETTING_INPUT_MESSAGE = System.lineSeparator() + "%s의 배팅 금액은?" + System.lineSeparator();
    private static final String MORE_CARD_INPUT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator();
    private static final String YES_INPUT = "y";
    private static final String NO_INPUT = "n";
    private static final String NAMES_SPLIT_REGEX = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println(NAME_INPUT_MESSAGE);
        String input = SCANNER.nextLine();
        validateNames(input);
        return Stream.of(input.split(NAMES_SPLIT_REGEX))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void validateNames(final String names) {
        if (names.isBlank()) {
            throw new IllegalArgumentException(NULL_NAMES_ERROR_MESSAGE);
        }
        if (Arrays.stream(names.split(NAMES_SPLIT_REGEX))
                .anyMatch(String::isBlank) || names.endsWith(NAMES_SPLIT_REGEX)) {
            throw new IllegalArgumentException(NAME_SIZE_MIN_ERROR_MESSAGE);
        }
    }

    public static int inputBetAmount(final String name) {
        System.out.printf(BETTING_INPUT_MESSAGE, name);
        int input = SCANNER.nextInt();
        SCANNER.nextLine();
        return input;
    }

    public static boolean inputIsDraw(final String name) {
        System.out.printf(MORE_CARD_INPUT_MESSAGE, name);
        String input = SCANNER.nextLine();
        validateAnswer(input);
        return input.equalsIgnoreCase(YES_INPUT);
    }

    private static void validateAnswer(final String answer) {
        if (!answer.equalsIgnoreCase(YES_INPUT) && !answer.equalsIgnoreCase(NO_INPUT)) {
            throw new IllegalArgumentException(NULL_ANSWER_YN_ERROR_MESSAGE);
        }
    }
}
