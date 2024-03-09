package blackjack.view;

import blackjack.exception.DelimiterFormatException;
import blackjack.exception.InvalidHitCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String PLAYERS_NAME_REQUEST = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_HIT_REQUEST = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Pattern HIT_REQUEST_PATTERN = Pattern.compile("^[yn]$");
    private static final String REQUEST_HIT = "y";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersName() {
        System.out.println(PLAYERS_NAME_REQUEST);
        String input = scanner.nextLine();
        validateDelimiter(input);
        return Arrays.stream(input.split(DELIMITER))
                .toList();
    }

    public boolean dosePlayerWantHit(String playerName) {
        System.out.println(playerName + PLAYER_HIT_REQUEST);
        String input = scanner.nextLine();
        validateHitRequest(input);
        return REQUEST_HIT.equals(input);
    }

    private void validateDelimiter(String input) {
        if (input.startsWith(DELIMITER) || input.endsWith(DELIMITER)) {
            throw new DelimiterFormatException();
        }
    }

    private void validateHitRequest(String input) {
        if (!HIT_REQUEST_PATTERN.matcher(input).matches()) {
            throw new InvalidHitCommandException(input);
        }
    }
}
