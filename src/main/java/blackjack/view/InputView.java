package blackjack.view;

import blackjack.domain.gamer.Player;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));
    private static final String PLAYER_NAME_DELIMITER = ",";
    private static final String COMMAND_YES = "y";
    private static final String COMMAND_NO = "n";
    private static final String BETTING_NUMBER_ERROR_MESSAGE = "배팅 금액은 숫자이어야 합니다.";
    private static final String COMMAND_ERROR_MESSAGE = String.format("%s 또는 %s만 입력할 수 있습니다.", COMMAND_YES, COMMAND_NO);
    private static final String DELIMITER_ERROR_MESSAGE = "플레이어의 이름은 쉼표로 시작하거나 끝날 수 없습니다.";

    private final Scanner scanner;

    private InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = scanner.nextLine().replace(" ", "");
        validatePlayerNamesFormat(names);

        return List.of(names.split(PLAYER_NAME_DELIMITER));
    }

    private static void validatePlayerNamesFormat(String names) {
        if (names.startsWith(PLAYER_NAME_DELIMITER) || names.endsWith(PLAYER_NAME_DELIMITER)) {
            throw new IllegalArgumentException(DELIMITER_ERROR_MESSAGE);
        }
    }

    public long readBettingAmount(Player player) {
        System.out.println(System.lineSeparator() + String.format("%s의 배팅 금액은?", player.getName()));
        String bettingAmount = scanner.nextLine();

        try {
            return Long.parseLong(bettingAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BETTING_NUMBER_ERROR_MESSAGE);
        }
    }

    public boolean readHitOrStand(Player player) {
        System.out.println(
                String.format("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)", player.getName(), COMMAND_YES, COMMAND_NO));
        String command = scanner.nextLine();
        validateCommand(command);

        return command.equals(COMMAND_YES);
    }

    private void validateCommand(String command) {
        if (!List.of(COMMAND_YES, COMMAND_NO).contains(command)) {
            throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
        }
    }
}
