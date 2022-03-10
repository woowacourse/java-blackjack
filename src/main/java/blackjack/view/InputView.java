package blackjack.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final String REQUEST_PLAYER_NAMES_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String REQUEST_MORE_PLAYER_CARD_INPUT_TEXT = "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)";
    private static final String INVALID_MORE_PLAYER_CARD_INPUT_EXCEPTION_MESSAGE = "y 혹은 n만 입력이 가능합니다.";

    private static final String NAME_INPUT_DELIMITER = ",";
    private static final String YES_TEXT = "y";
    private static final String NO_TEXT = "n";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestPlayerNamesInput() {
        print(REQUEST_PLAYER_NAMES_INPUT_MESSAGE);
        String input = scanner.nextLine();

        return getTrimmedStringListOf(input);
    }

    private static List<String> getTrimmedStringListOf(String input) {
        return Stream.of(input.split(NAME_INPUT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void print(String text) {
        System.out.println(text);
    }

    public static boolean requestMorePlayerCardInput(String playerName) {
        print(playerName + REQUEST_MORE_PLAYER_CARD_INPUT_TEXT);
        String input = scanner.nextLine();

        validateInvalidMoreCardInput(input);
        return input.equals(YES_TEXT);
    }

    private static void validateInvalidMoreCardInput(String input) {
        if (!input.equals(YES_TEXT) && !input.equals(NO_TEXT)) {
            throw new IllegalArgumentException(INVALID_MORE_PLAYER_CARD_INPUT_EXCEPTION_MESSAGE);
        }
    }
}
