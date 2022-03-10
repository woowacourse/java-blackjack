package blackJack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String INPUT_MESSAGE_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_DELIMITER_PLAYER_NAMES = ",";
    private static final String INPUT_MESSAGE_ONE_MORE_CARD =
        "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".concat(NEWLINE);

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_MESSAGE_PLAYER_NAMES);
        return splitInputPlayerNames(scanner.nextLine());
    }

    private static List<String> splitInputPlayerNames(String input) {
        return Arrays.stream(input.split(INPUT_DELIMITER_PLAYER_NAMES))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static String inputOneMoreCard(String name) {
        System.out.printf(INPUT_MESSAGE_ONE_MORE_CARD, name);
        return scanner.nextLine();
    }
}
