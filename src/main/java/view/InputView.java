package view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String SPLIT_DELIMITER = ",";
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NEWLINE = System.lineSeparator();

    private InputView() {
    }

    public static List<String> askPlayerNames() {
        System.out.println(NEWLINE + ASK_PLAYER_NAMES);
        String playerNames = Console.readLine();
        return splitInputByDelimiter(playerNames);
    }

    private static List<String> splitInputByDelimiter(String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
            .map(String::strip)
            .toList();
    }
}
