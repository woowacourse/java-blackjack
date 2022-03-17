package blackjack.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_PLAYER_BET_MONEY_MESSAGE = "%s의 배팅 금액은?%n";
    private static final String INPUT_HIT_COMMAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";

    private static final String PLAYER_NAME_DELIMITER = ",";

    private InputView() {
        throw new AssertionError();
    }

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        return Arrays.stream(Console.readLine().split(PLAYER_NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public static int inputPlayerBetMoney(final String name) {
        System.out.printf(INPUT_PLAYER_BET_MONEY_MESSAGE, name);
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("배팅 금액은 숫자만 입력가능합니다.");
        }
    }

    public static String inputHitCommand(final String currentPlayerName) {
        System.out.printf(INPUT_HIT_COMMAND_MESSAGE, currentPlayerName);
        return Console.readLine();
    }
}
