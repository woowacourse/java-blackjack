package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_COMMAND_MESSAGE = "%s은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INPUT_DELIMITER = ",";

    public List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        final String input = scanner.nextLine();
        return Arrays.stream(input.split(INPUT_DELIMITER, -1))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public DrawCommand inputCommand(final String playerName) {
        System.out.println(String.format(INPUT_COMMAND_MESSAGE, playerName));
        final String input = scanner.nextLine();
        return DrawCommand.from(input);
    }
}
