package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_HIT_COMMAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";

    private static final String PLAYER_NAME_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        return Arrays.stream(scanner.nextLine().split(PLAYER_NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public static String inputHitCommand(final String playerName) {
        System.out.printf(INPUT_HIT_COMMAND_MESSAGE, playerName);
        return scanner.nextLine();
    }
}
