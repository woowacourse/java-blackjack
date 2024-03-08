package blackjack.view;

import blackjack.exception.DelimiterFormatException;
import blackjack.exception.InvalidHitCommandException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String PLAYERS_NAME_REQUEST = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_HIT_REQUEST = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersName() {
        System.out.println(PLAYERS_NAME_REQUEST);
        String input = scanner.nextLine();
        validateDelimiter(input);
        return Arrays.stream(input.split(DELIMITER))
                .toList();
    }

    public String readPlayerActionCommand(String playerName) {
        System.out.println(playerName + PLAYER_HIT_REQUEST);
        return scanner.nextLine();
    }

    private void validateDelimiter(String input) {
        if (input.startsWith(DELIMITER) || input.endsWith(DELIMITER)) {
            throw new DelimiterFormatException();
        }
    }
}
