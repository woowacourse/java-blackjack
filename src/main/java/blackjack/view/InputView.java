package blackjack.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final String REQUEST_PLAYER_NAMES_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_INPUT_DELIMITER = ",";

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
}
